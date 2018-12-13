package AdapterExecutionPool;

import java.io.Serializable;

public class Lecture implements Serializable
{
    public Lecture(String value, long timestamp,int serviceId) {
        this.value = value;
        this.timestamp = timestamp;
        this.serviceId=serviceId;
    }
    
    private int serviceId;

    public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private String value;
    private long timestamp;

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    private int service;
}
