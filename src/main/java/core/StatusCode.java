package core;

public enum StatusCode {

    SUCCESS(200, "Request is successful"),

    CREATED(201, "New resource created successfully"),

    BAD_REQUEST(400, "Missing required field"),

    UNAUTHORIZED(401,"Invalid Access Token"),

    NOT_FOUND(404, "Resource not found"),

    NO_CONTENT(204, "No Content Success status code");


    public final int code;

    public final String msg;

    StatusCode(int code, String msg){
        this.code = code;
        this.msg = msg;

    }
}
