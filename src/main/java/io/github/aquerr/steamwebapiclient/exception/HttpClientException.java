package io.github.aquerr.steamwebapiclient.exception;

public class HttpClientException extends ClientException
{
    private final int statusCode;

    public HttpClientException(String message, int statusCode)
    {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode()
    {
        return statusCode;
    }
}
