import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Random;

public class DiffieHellman 
{
	static int p,q;
	int secret_key;
	BigInteger Y_self,Y_other,K;
	
	public DiffieHellman()
	{
		//Generate random secret key
		Random random = new Random();
		this.secret_key=random.nextInt(100);		
	}
	
	BigInteger computeY()
	{
		BigInteger bg=new BigInteger(String.valueOf(q));
		BigInteger bg1=new BigInteger(String.valueOf(this.secret_key));
		BigInteger bg2=new BigInteger(String.valueOf(p));
		BigInteger bg3=bg.modPow(bg1, bg2);
		//int answer=(int)(Math.pow(q, this.secret_key))%p;
		//answer=answer%p;
		return bg3;
	}
	
	BigInteger computeK()
	{
		BigInteger bg=new BigInteger(String.valueOf(this.Y_other));
		BigInteger bg1=new BigInteger(String.valueOf(this.secret_key));
		BigInteger bg2=new BigInteger(String.valueOf(p));
		BigInteger bg3=bg.modPow(bg1, bg2);
		//int answer=(int)(Math.pow(this.Y_other,this.secret_key))%p;
		//answer=answer%p;
		return bg3;
	}

	public static void main(String[] args) 
	{
		Scanner in=new Scanner(System.in);
		
		System.out.println("Enter a prime number p");
		p=in.nextInt();
		System.out.println("Enter a primitive root of p");
		q=in.nextInt();
		
		DiffieHellman personA=new DiffieHellman();
		DiffieHellman personB=new DiffieHellman();
		
		System.out.println("Secret key of A "+personA.secret_key);
		System.out.println("Secret key of B "+personB.secret_key);
		
		// Calculate public keys
		personA.Y_self=personA.computeY();
		System.out.println("Y(A):"+personA.Y_self);
		personB.Y_self=personB.computeY();
		System.out.println("Y(B):"+personB.Y_self);
		
		//Exchange public keys
		personA.Y_other=personB.Y_self;
		personB.Y_other=personA.Y_self;
		
		//Compute common session key
		personA.K=personA.computeK();
		System.out.println("K(A):"+personA.K);
		personB.K=personB.computeK();
		System.out.println("K(B):"+personB.K);
		
		
		if(personA.K.equals(personB.K))
		{
			System.out.println("Session Key: "+personA.K);
			System.out.println("Person 1 and 2 can communicate");
		}
		else
		{
			System.out.println("Person 1 and 2 cannot communicate");
		}
		
	}

}
