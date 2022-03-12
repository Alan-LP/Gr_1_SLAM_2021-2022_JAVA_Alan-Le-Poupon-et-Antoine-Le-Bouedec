package gsb.utils;

public class ServiceUtils {
	
	public static boolean isStringNumeric(String uneChaine)
	{
		boolean isNumeric = true;
		
		for(int i = 0; i < uneChaine.length(); i ++)
		{
			char unChar = uneChaine.charAt(i);
			if(!(unChar >= '0' && unChar <= '9'))
			{
				isNumeric = false;
			}
		}
		
		return isNumeric;
	}
	
	public static boolean isAReference(String reference)
	{
		boolean isRef = true;
		
		if(reference.length() != 5)
		{
			isRef = false;
		}
		else
		{
			for(int i = 0; i < reference.length(); i ++)
			{
				char unChar = reference.charAt(i);
				if(i == 0)
				{
					if(unChar != 'v')
						isRef = false;
				}
				else
				{
					if(!(unChar >= '0' && unChar <= '9'))
						isRef = false;
				}
			}
		}
		
		return isRef;
	}
	
	public static boolean isADate(String date)
	{
		boolean isDate = true;
		
		if(date.length() != 10)
		{
			isDate = false;
		}
		else
		{
			for(int i = 0; i < date.length(); i ++)
			{
				char unChar = date.charAt(i);
				if(i == 4 || i == 7)
				{
					if(unChar != '-')
						isDate = false;
				}
				else
				{
					if(!(unChar >= '0' && unChar <= '9'))
						isDate = false;
				}
			}
		}
		
		return isDate;
	}
	
	public static boolean isAMatricule(String matricule)
	{
		boolean isMatricule = true;
		
		if(matricule.length() > 4 || matricule.length() < 3)
		{
			isMatricule = false;
		}
		else
		{
			for(int i = 0; i < matricule.length(); i ++)
			{
				char unChar = matricule.charAt(i);
				if(i == 0)
				{
					if(unChar < 'a' || unChar > 'z')
						isMatricule = false;
				}
				else
				{
					if(unChar < '0' || unChar > '9')
						isMatricule = false;
				}
			}
		}
		
		return isMatricule;
	}
	
	public static boolean isACodeMed(String codeMed)
	{
		boolean isCodeMed = true;
		
		if(codeMed.length() != 4)
		{
			isCodeMed = false;
		}
		else
		{
			for(int i = 0; i < codeMed.length(); i ++)
			{
				char unChar = codeMed.charAt(i);
				if(i == 0)
				{
					if(unChar != 'm')
						isCodeMed = false;
				}
				else
				{
					if(!(unChar >= '0' && unChar <= '9'))
						isCodeMed = false;
				}
			}
		}
		
		return isCodeMed;
	}
	
	public static boolean isADepotLegal(String depot)
	{
		boolean isDep = true;
		
		if(depot.length() > 10 || depot.length() < 5)
		{
			isDep = false;
		}
		else
		{
			for(int i = 0; i < depot.length(); i ++)
			{
				char unChar = depot.charAt(i);
				if(!((unChar >= '0' && unChar <= '9') || (unChar >= 'A' && unChar <= 'Z')))
				{
					isDep = false;
				}
			}
		}
			
		return isDep;
	}
	
}
