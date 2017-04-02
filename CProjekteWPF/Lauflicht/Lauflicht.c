#include <avr/io.h>
#define F_CPU 8000000
#include <util/delay.h>

// ### Array für die Ports ###

// Array PORTB
uint8_t dataB[8] = { PB0, PB1, PB2,PB3, PB4, PB5, PB6, PB7 };
// Array PORTC
uint8_t dataC[8] = { PC0, PC1, PC2,PC3, PC4, PC5, PC6, PC7 };

void butzer ()
{
	PORTB = 0b11111111;
	PORTC = 0b11111111;
}

void verzoegerung ()
{
	_delay_ms(100);
}

void mache ()
{
	butzer ();
	for ( int i = 0; i < 8; i++)
	{
		PORTB &= ~(1 << dataB[i]);
		PORTC &= ~(1 <<	dataC[i]);
		verzoegerung ();
		butzer ();	
	}
}



int main (void)
{
	DDRB = 0b11111111;
	DDRC = 0b11111111;
	DDRD = 0b11100000;
	int started = 0;

	int i = 0;

	while (1)
	{	
		butzer ();

		if(started == 0){
			loop_until_bit_is_set (PIND, PD3);
		}
		
		started = 1;
		
		if (i == 4)
		{
			butzer();
			started = 0;
			i = 0;
		}

		mache();

		i++;
	}

	return 0;
}



