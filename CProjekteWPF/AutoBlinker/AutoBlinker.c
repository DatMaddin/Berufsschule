#include <avr/io.h>
#define F_CPU 8000000
#include <util/delay.h>

int main (void)
{
	DDRD = 0b11100000;

	int linkerBlinker = 0;
	int rechterBlinker = 0;
	

	while (1)
	{
		if (PIND & (1 << PD3))
		{
			if (linkerBlinker)
			{
				linkerBlinker = 0;
			}
			else
			{
				linkerBlinker = 1;
			}
		}

		if (PIND & (1 << PD4))
		{
			if (rechterBlinker)
			{
				rechterBlinker = 0;
			}
			else
			{
				rechterBlinker = 1;
			}
		}

		if (linkerBlinker)
		{
			PORTD |= (1 << PD5);
			_delay_ms(200);
			PORTD &= ~(1 << PD5);
			_delay_ms(200);
		}
		else
		{
			PORTD &= ~(1 << PD5);
		}

		if (rechterBlinker)
		{
			PORTD |= (1 << PD6);
			_delay_ms(200);
			PORTD &= ~(1 << PD6);
			_delay_ms(200);
		}
		else
		{
			PORTD &= ~(1 << PD6);
		}


		//BUTZER
		if (PIND & (1 << PD2))
		{
			PORTD |= (1 << PD7);
		}
		else
		{
			PORTD &= ~(1 <<PD7);
		}
	}


	return 0;
}
