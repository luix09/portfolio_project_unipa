#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include "string_util.h"

int main() {
    // Dichiarazione delle variabili
    int unix_server_socket, first_client, second_client;
    struct sockaddr_in serverAddr;
    struct sockaddr_storage serverStorage;
    socklen_t addr_size;
    char buffer[1024];

    // Inizializzazione del socket del server
    unix_server_socket = socket(PF_INET, SOCK_STREAM, 0);
    serverAddr.sin_family = PF_INET;
    serverAddr.sin_port = htons(7891);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.01");
    memset(serverAddr.sin_zero, '\0', sizeof serverAddr.sin_zero);
    bind(unix_server_socket, (struct sockaddr *) &serverAddr, sizeof(serverAddr));

    // Inizio dell'ascolto delle connessioni in entrata
    if (listen(unix_server_socket,5)==0)
        printf("Listening\n");
    else
        printf("Error\n");

    // Accettazione delle connessioni dai client
    addr_size = sizeof serverStorage;
    first_client = accept(unix_server_socket, (struct sockaddr *) &serverStorage, &addr_size);
    second_client = accept(unix_server_socket, (struct sockaddr *) &serverStorage, &addr_size);

    int cmdEXIT = 0;
    
    while (cmdEXIT == 0)
    {
        // Ricezione del messaggio dal Client1 e invio al Client2
        recv(first_client, buffer, 1024, 0);
        printf ("%s\nInvia al Client2\n", buffer);
        send(second_client,buffer,1024,0);
        if (compare_strings(buffer, "exit")==0)
        {   
            cmdEXIT = 1;
        }
        else 
        {
            // Azzera il buffer e ricevi il messaggio dal Client2
            memset(&buffer[0], 0, sizeof(buffer));    
            recv(second_client, buffer, 1024, 0);
            printf ("%s\nInvia al Client1\n", buffer);
            // Invia il messaggio ricevuto dal Client2 al Client1
            send(first_client,buffer,1024,0);
            if (compare_strings(buffer, "exit")==0)
            {
                cmdEXIT = 1;
            }
        }
    }

    return 0;
}