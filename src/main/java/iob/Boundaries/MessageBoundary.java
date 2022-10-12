package iob.Boundaries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import iob.StatusEnum;

/*
{
  "id":1,
  "message":"hello".
  "messageTimestamp":"2022-03-13T12:18:42.865+00:00",
  "important":true,
  "name":{
    "first":"Jane",
    "last":"Smith",
    "middle":["J", "R", "Janie"]
  },
  "complex":{
    "x":1.29,
    "y":9,
    "name":{
      "first":"Jane",
      "last":"Smith",
      "middle":["J", "R", "Janie"]
    }
  },
  "details":{},
  "status":"unknown"
}
 */
public class MessageBoundary {
	private Long id;
	private String message;
	private Date messageTimestamp;
	private Boolean important;
	private NameBoundary name;
	private ComplexBoundary complex;
	private Map<String, Object> details;
	private StatusEnum status;

	public MessageBoundary() {
		this.details = new HashMap<>();
	}

	public MessageBoundary(String message) {
		this();
		this.message = message;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessageTimestamp() {
		return messageTimestamp;
	}

	public void setMessageTimestamp(Date messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public NameBoundary getName() {
		return name;
	}

	public void setName(NameBoundary name) {
		this.name = name;
	}

	public ComplexBoundary getComplex() {
		return complex;
	}

	public void setComplex(ComplexBoundary complex) {
		this.complex = complex;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", messageTimestamp=" + messageTimestamp + ", important=" + important
				+ ", name=" + name + ", complex=" + complex + ", details=" + details + ",status=" + status + "]";
	}

}
