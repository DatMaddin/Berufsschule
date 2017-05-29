#include <avr/io.h>
#include <stdlib.h>
#define F_CPU 8000000
#include <util/delay.h>
#include <avr/interrupt.h> 


int delay = 3000;
int delayAmpelAn = 5000;
int delayInput = 0;
void shutdownAll();
//           AF     AS    AP
int ampelAn[3] = {0, 0, 0}; 


int main (void)
{
	/* externe Interrupt initialisieren */
	MCUCR = 0b00001111;
	GICR = 0b11000000;	//alt GIMSK
	/*----------------------------------*/	

	DDRD = 0b11100000;
	DDRC = 0b11111111;

	
	delay = 500;
	sei();

	while (1)
	{	
		//PORTC = 0b10001100;
		//ampelAn[1] = 1;
		delayInput++;
	}
	return 0;
}

void shutDownAll(){
	
	PORTC |= (1 << PC6);
	PORTC &= ~(1 << PC7);

	if (ampelAn[1] == 1)
	{
		PORTC |= (1 << PC4);
		PORTC &= ~(1 << PC5);
		PORTC &= ~(1 << PC3);
		ampelAn[1] = 0;
	} 
	
	if (ampelAn[2] == 1)
	{
		PORTC |= (1 << PC1);
		PORTC &= ~(1 << PC2);
		PORTC &= ~(1 << PC0);
		ampelAn[2] = 0;
	} 

	_delay_ms(delay);

	PORTC = 0b10100100;
}



// linker Taster
// Fußgänger Ampel
ISR(INT0_vect)
{
		shutDownAll();

		PORTC = 0b01100100;
		ampelAn[0] = 1;
		_delay_ms(delayAmpelAn);
		shutDownAll();
		_delay_ms(2000);
		PORTC = 0b10110100;
		_delay_ms(2000);
		PORTC = 0b10001100;
		ampelAn[0] = 0;
		ampelAn[1] = 1;
}
// rechter Taster
// Parkhaus-Ausfahrt
ISR(INT1_vect)
{
	shutDownAll();
	

	PORTC = 0b10100110;
	_delay_ms(2000);
	PORTC = 0b10100001;
	ampelAn[2] = 1;
	_delay_ms(delayAmpelAn);
	shutDownAll();
	_delay_ms(2000);
	PORTC = 0b10110100;
	_delay_ms(2000);
	PORTC = 0b10001100;
	ampelAn[2] = 0;
	ampelAn[1] = 1;
}
