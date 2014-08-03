package wf.jevy.servicespool.conf;

public class Service {
	String serivceScope = null;
	String serviceName = null;
	String SQL = null;
	boolean state = false;
	String version = null;

	public String getSerivceScope() {
		return serivceScope;
	}
	public void setSerivceScope(String serivceScope) {
		this.serivceScope = serivceScope;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSQL() {
		return SQL;
	}
	public void setSQL(String SQL) {
		this.SQL = SQL;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
