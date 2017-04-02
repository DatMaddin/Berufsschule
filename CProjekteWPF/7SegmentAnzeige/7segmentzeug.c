// clock speed for delay
#define F_CPU 8000000

#include <avr/io.h>
#include <util/delay.h>

int main (void)
{
	DDRB = 0b11111111;
	DDRC = 0b11111111;

	PORTB = 0b00000000;

	int count = 0;
	int zehner = 0;

	uint8_t data[10] = {0b11000000, // 0
						0b11111001, // 1
						0b10100100, // 2
						0b10110000, // 3
						0b10011001,	// 4
						0b10010010,	// 5
						0b10000010, // 6
						0b11111000, // 7
						0b10000000, // 8
						0b10010000 }; // 9

	while (1)
	{	

		if (count > 9)
		{
			count = 0;
			zehner++;
		}
		
		for(int i = 0; i < 100; i++){
		PORTB &= ~(1<< PB0);
		PORTB |=  (1<< PB1);
		PORTB |=  (1<< PB2);
		PORTB |=  (1<< PB3);
				
		PORTC = data[count];
		
		_delay_ms(5);
		
		PORTB &= ~(1<< PB1);
		PORTB |=  (1<< PB0);
		PORTB |=  (1<< PB2);
		PORTB |=  (1<< PB3);
				
		PORTC = data[zehner];
		_delay_ms(5);
		}

		count++;

	}
	return 0;
}
