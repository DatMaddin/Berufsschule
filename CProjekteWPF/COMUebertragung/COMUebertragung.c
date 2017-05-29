#include <avr/io.h>
#include <stdlib.h>
#include <util/delay.h>

void uart_init(void);
void uart_putc(unsigned char c);
void uart_puts(char *s);
uint8_t uart_getc(void);

int main (void)
{
	int wert1 = 5;
	char s[25];
	DDRD = 0b11100000;

	uart_init();
	uart_puts("HalloWelt");
	
	while (1)
	{

		
		loop_until_bit_is_set (PIND, PD3);

		for (int i = 48; i <= 57; i++)
		{
			uart_putc(i);
		}

		uart_putc(10);
		uart_putc(13);
		itoa(wert1, s, 10);
		uart_puts("Wert1: ");
		uart_puts(s);
		_delay_ms(2000);

	}
	return 0;
}


// UART-Init (z.B. für ATMega 16 / 8MHz)
void uart_init(void)
{
	UCSRB |= (1<<TXEN) | (1<<RXEN);
	UCSRC = (1<<URSEL) | (1<<USBS) | (1<<UCSZ1) | (1<<UCSZ0); // Asynchron 8N1 
	// 8N1 = 8 = 8 Datenbits, n = keine Paritätsprüfung, 1 = 1 Stopbit
	UBRRL = 51;
	UBRRH = 0; // Übertragung von 9600 Bit/s
}

// Zeichen senden
void uart_putc(unsigned char c)
{
	// warten bis senden möglich
	while (!(UCSRA & (1<<UDRE)));
	// senden
	UDR = c;
}

// Zeichenkette senden
void uart_puts(char *s)
{
	// solange s!= '\0' also != String-Endzeichen
	while (*s)
	{
		uart_putc(*s);
		s++;
	}
}

// Zeichen empfangen
uint8_t uart_getc(void)
{
	while (!(UCSRA & (1<<RXC))); // warten bis Zeichen verfügbar
	return UDR;	// Zeichen aus UDR an Aufrufer zurückgeben
}
