package irctc;

public class ModelTable 
{
	String tname,tclass,source,destination,date;

	public ModelTable(String tname, String tclass, String source, String destination, String date)
	{
		this.tname = tname;
		this.tclass = tclass;
		this.source = source;
		this.destination = destination;
		this.date = date;
	}

	public String getTname() 
	{
		return tname;
	}

	public void setTname(String tname) 
	{
		this.tname = tname;
	}

	public String getTclass() 
	{
		return tclass;
	}

	public void setTclass(String tclass) 
	{
		this.tclass = tclass;
	}

	public String getSource() 
	{
		return source;
	}

	public void setSource(String source) 
	{
		this.source = source;
	}

	public String getDestination() 
	{
		return destination;
	}

	public void setDestination(String destination) 
	{
		this.destination = destination;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}
}
