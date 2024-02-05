package irctc;

public class ModelTable2 
{
	String tid,tname,tclass,source,destination,stp_nmbr,arr_time,dep_time,src_dstn;

	public ModelTable2(String tid, String tname, String tclass, String source, String destination, String stp_nmbr,String arr_time, String dep_time, String src_dstn)
	{
		this.tid = tid;
		this.tname = tname;
		this.tclass = tclass;
		this.source = source;
		this.destination = destination;
		this.stp_nmbr = stp_nmbr;
		this.arr_time = arr_time;
		this.dep_time = dep_time;
		this.src_dstn = src_dstn;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTclass() {
		return tclass;
	}

	public void setTclass(String tclass) {
		this.tclass = tclass;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStp_nmbr() {
		return stp_nmbr;
	}

	public void setStp_nmbr(String stp_nmbr) {
		this.stp_nmbr = stp_nmbr;
	}

	public String getArr_time() {
		return arr_time;
	}

	public void setArr_time(String arr_time) {
		this.arr_time = arr_time;
	}

	public String getDep_time() {
		return dep_time;
	}

	public void setDep_time(String dep_time) {
		this.dep_time = dep_time;
	}

	public String getSrc_dstn() {
		return src_dstn;
	}

	public void setSrc_dstn(String src_dstn) {
		this.src_dstn = src_dstn;
	}
	
}
