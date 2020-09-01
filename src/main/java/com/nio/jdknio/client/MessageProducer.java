package com.nio.jdknio.client;

import com.nio.jdknio.utils.MessageUtils;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 消息生产者
 *
 * @author Yue
 * @date 2020/9/1
 */
public class MessageProducer implements Runnable {

    private SocketChannel socketChannel;

    public MessageProducer(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        while (true) {
            //从控制台输入
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            try {
                MessageUtils.sendMessage(msg, socketChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
