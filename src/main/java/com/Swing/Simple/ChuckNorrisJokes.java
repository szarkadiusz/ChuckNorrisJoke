package com.Swing.Simple;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChuckNorrisJokes implements ActionListener {
    private JPanel panel;
    private JFrame frame;
    private JButton button;
    private static JTextArea textArea;
    public ChuckNorrisJokes() {
        button = new JButton("Laduj żart");
        button.setSize(600,50);
        button.addActionListener(actionEvent-> {
            try {
                newJoke();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(600,100));
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,0));
        panel.setSize(800,800);
        panel.add(button);
        panel.add(textArea);


        panel.setBorder(BorderFactory.createEmptyBorder(10,50,10,50));

        frame=new JFrame();
        frame.add(panel);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

    }

    public static void main(String[] args) {
        new ChuckNorrisJokes();


    }

    public static void newJoke () throws IOException {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create("https://api.chucknorris.io/jokes/random")).build();
        HttpResponse <String>httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        String resp = httpResponse.body ();

        ChuckNorrisResponse chuckResponse=mapper.readValue(resp,ChuckNorrisResponse.class);

        textArea.setText(chuckResponse.getValue());

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
