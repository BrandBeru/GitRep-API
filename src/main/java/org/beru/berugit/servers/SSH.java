/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.beru.berugit.servers;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.ByteArrayOutputStream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author brand
 */
@Getter
@NoArgsConstructor
@Component
public class SSH {

    @Value("${beru.ssh.user}")
    private String user;
    @Value("${beru.ssh.password}")
    private String password;
    @Value("${beru.ssh.port}")
    private String port;
    @Value("${beru.ssh.host}")
    private String host;

    private Session session;
    private ChannelExec channel;

    public boolean connect() {
        try {
            session = new JSch().getSession(user, host);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println(String.format("User: %s - password: %s", user, password));
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");

            return true;
        } catch (JSchException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean createFolder(String folder, String username) {
        try {
            channel.setCommand(String.format("cd ~/ && mkdir -p %s/%s.git && cd ~/%s/%s.git && git init --bare", username, folder, username, folder));
            execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean clone(String folder, String username){
        try{
            channel.setCommand(String.format("cd ~/%s && rm -R %s || git clone %s.git/", username, folder, folder));
            execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean removeFolder(String folder, String user) {
        try {
            channel.setCommand(String.format("cd ~/%s && rm -R %s.git", user, folder));
            execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void execute() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        channel.setOutputStream(out);
        channel.connect();
        
        while(channel.isConnected()){
            Thread.sleep(100);
        }
        
        String response = new String(out.toByteArray());
        System.out.println(response);
        
        channel.getExitStatus();

        channel.disconnect();
        session.disconnect();
    }

}
