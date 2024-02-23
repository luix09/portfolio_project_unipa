#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include "string_util.h"


int main() {
    int clientSocket;
    char buffer[1024];
    struct sockaddr_in serverAddr;
    socklen_t addr_size;
    int cmdEXIT = 0;

    // Creazione del socket del client
    clientSocket = socket(PF_INET, SOCK_STREAM, 0);

    // Controllo che la creazione del socket sia andata a buon fine
    if (clientSocket == -1) {
        perror("Errore durante la creazione del socket");
        exit(EXIT_FAILURE);
    }

    // Configurazione dell'indirizzo del server
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(7891);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    memset(serverAddr.sin_zero, '\0', sizeof serverAddr.sin_zero);
    addr_size = sizeof serverAddr;

    // Controllo che la connessione tra server e client sia andata a buon fine
    if (connect(clientSocket, (struct sockaddr*) &serverAddr, addr_size) == -1) {
        perror("Errore durante la connessione al server");
        close(clientSocket);
        exit(EXIT_FAILURE);
    }

    printf("Client 1 : ");
    // Aspetto messaggio dal terminale elo memorizzo nel buffer
    scanf(" %[^\n]s", buffer);
    // Invio messaggio ricevuto da stdin
    send(clientSocket,buffer,sizeof buffer - 1,0);      

    while (cmdEXIT == 0)
    {
        if (compare_strings(buffer, "exit")==-1)
        {
            // Imposto i bit del buffer a 0
            memset(&buffer[0], 0, sizeof(buffer));
            // Attendo ricezione del messaggio dal client2 sul socket
            int recvValue = recv(clientSocket, buffer, sizeof buffer - 1, 0);
            if (recvValue != 1)
            {
                if (compare_strings(buffer, "exit")==-1)
                {
                    printf("Client 2 : ");
                    printf("%s\n", buffer);
                    memset(&buffer[0], 0, sizeof(buffer));
                }
                else cmdEXIT=1;
            }
            else
            {
                printf("Client 1 : ");
                scanf(" %[^\n]s", buffer);
                send(clientSocket,buffer,sizeof buffer - 1,0);
            }
        }
        else cmdEXIT=1;
    }

    return 0;
}