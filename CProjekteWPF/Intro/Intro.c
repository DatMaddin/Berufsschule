#include <avr/io.h>
#define F_CPU 8000000 // in Hz
#include <util/delay.h>

int main (void)
{
	DDRD = 0b11100000;
	

	while (1)
	{
		_delay_ms(50);
		PORTD |= (1 << PD5);

		_delay_ms(50;
		PORTD &= ~(1 << PD5);
		
	}


	return 0;
}
