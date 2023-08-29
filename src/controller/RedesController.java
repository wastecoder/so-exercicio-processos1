package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
    //O primeiro, chamado os, que identifica e retorna o nome do Sistema Operacional (Fazê-lo privado)
    private String os() {
        if (System.getProperty("os.name").contains("Windows")) {
            return "Windows";
        } else {
            return "Linux";
        }
    }

    //O segundo, chamado ip, que verifica o Sistema Operacional e, conforme o S.O., faz a chamada de configuração de IP.
    //A leitura do processo chamado deve verificar cada linha e, imprimir, apenas, o nome do adaptador de rede e o IPv4, portanto, adaptadores sem IPv4 não devem ser mostrados
    public void ip() {
        String os = this.os();
        StringBuilder process = new StringBuilder();

        //Comando no terminal dependendo SO
        if (os.equals("Windows")) {
            process.append("ipconfig");
        } else {
            process.append("ifconfig");
        }

        try {
            //Copia o resultado do comando no buffer
            Process p = Runtime.getRuntime().exec(process.toString());
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);

            //Printa apenas o IPv4 no buffer
            String linha;
            while ((linha = buffer.readLine()) != null) {
                if (os.equals("Windows")) {
                    String[] split = linha.split(":");
                    if (split[0].contains("IPv4")) {
                        System.out.println("Endereço IPv4:" + split[1]);
                    }
                } else {
                    linha = linha.trim();
                    String[] split = linha.split(" ");
                    if (split[0].contains("inet")) {
                        System.out.println("Endereço IPv4: " + split[1]);
                    }
                }
            }

            buffer.close();
            leitor.close();
            fluxo.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //O terceiro, chamado ping, que verifica o Sistema Operacional e, conforme o SO, faz a chamada de ping em IPv4 com 10 iterações.
    //A leitura do processo chamado deve verificar as linhas de saída e exibir, apenas, o tempo médio do ping. O teste de ping deve ser feito com a URL www.google.com.br
    public void ping() {
        String os = this.os();
        StringBuilder process = new StringBuilder();

        if (os.equals("Windows")) {
            process.append("ping -4 -n 10 www.google.com.br");
        } else {
            process.append("ping -4 -c 10 www.google.com.br");
        }

        try {
            System.out.println(">>> AGUARDE: Esperando a média dos 10 pings!");
            Process p = Runtime.getRuntime().exec(process.toString());
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);

            //Copia apenas a última linha no buffer
            String linha, ultima = "";
            while ((linha = buffer.readLine()) != null) {
                ultima = linha;
            }

            buffer.close();
            leitor.close();
            fluxo.close();

            //Printa a média de ping no terminal
            if (os.equals("Windows")) {
                String[] split = ultima.split(" = ");
                System.out.println("Média = " + split[split.length - 1]);
            } else {
                String[] split = ultima.split("/");
                System.out.println("Média = " + split[split.length - 3]);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
