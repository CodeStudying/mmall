package com.man.mmall.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");
    private ChannelSftp sftp = null;
    private Session sshSession = null;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static void uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 22, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器");
        ftpUtil.uploadFile("/home/files", fileList);
        logger.info("开始连接ftp服务器,结束上传,上传结果:{}");
    }


    private void uploadFile(String remotePath, List<File> fileList){
        FileInputStream fis = null;
        jSch = new JSch();
        //连接FTP服务器
            try {
                sshSession = jSch.getSession(user, ip, port);
                sshSession.setPassword(pwd);
                //严格主机密钥检查
                sshSession.setConfig("StrictHostKeyChecking", "no");

                //开启sshSession链接
                sshSession.connect();
                //获取sftp通道
                Channel channel = sshSession.openChannel("sftp");
                //开启
                channel.connect();
                sftp = (ChannelSftp) channel;
                if (sftp != null) {
                    for (File fileItem : fileList) {
                        sftp.cd(remotePath);
                        sftp.put(new FileInputStream(fileItem), fileItem.getName());
                    }
                }

            } catch (Exception e) {
                logger.error("上传文件异常", e);
                throw new RuntimeException(e);
            } finally {
                sftp.disconnect();
                sftp.exit();
                sshSession.disconnect();
            }
    }



    private String ip;
    private int port;
    private String user;
    private String pwd;
    private JSch jSch;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public JSch getjSch() {
        return jSch;
    }

    public void setjSch(JSch jSch) {
        this.jSch = jSch;
    }
}
