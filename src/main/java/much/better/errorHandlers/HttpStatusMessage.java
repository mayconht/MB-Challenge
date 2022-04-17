package much.better.errorHandlers;

import java.util.HashMap;
import java.util.Map;

public class HttpStatusMessage {
    private static final Map<Integer, String> MESSAGES = new HashMap<>();

    static {
        // 1xx Informational
        MESSAGES.put(100, "Continue");
        MESSAGES.put(101, "Switching Protocols");
        MESSAGES.put(102, "Processing");
        MESSAGES.put(103, "Early Hints");

        // 2xx Success
        MESSAGES.put(200, "OK");
        MESSAGES.put(201, "Created");
        MESSAGES.put(202, "Accepted");
        MESSAGES.put(203, "Non-Authoritative Information");
        MESSAGES.put(204, "No Content");
        MESSAGES.put(205, "Reset Content");
        MESSAGES.put(206, "Partial Content");
        MESSAGES.put(207, "Multi-Status");
        MESSAGES.put(208, "Already Reported");
        MESSAGES.put(226, "IM Used");

        // 3xx Redirection
        MESSAGES.put(300, "Multiple Choices");
        MESSAGES.put(301, "Moved Permanently");
        MESSAGES.put(302, "Found");
        MESSAGES.put(303, "See Other");
        MESSAGES.put(304, "Not Modified");
        MESSAGES.put(305, "Use Proxy");
        MESSAGES.put(306, "Switch Proxy");
        MESSAGES.put(307, "Temporary Redirect");
        MESSAGES.put(308, "Permanent Redirect");

        // 4xx Client Errors
        MESSAGES.put(400, "Bad Request");
        MESSAGES.put(401, "Unauthorized");
        MESSAGES.put(402, "Payment Required");
        MESSAGES.put(403, "Forbidden");
        MESSAGES.put(404, "Not Found");
        MESSAGES.put(405, "Method Not Allowed");
        MESSAGES.put(406, "Not Acceptable");
        MESSAGES.put(407, "Proxy Authentication Required");
        MESSAGES.put(408, "Request Timeout");
        MESSAGES.put(409, "Conflict");
        MESSAGES.put(410, "Gone");
        MESSAGES.put(411, "Length Required");
        MESSAGES.put(412, "Precondition Failed");
        MESSAGES.put(413, "Payload Too Large");
        MESSAGES.put(414, "URI Too Long");
        MESSAGES.put(415, "Unsupported Media Type");
        MESSAGES.put(416, "Range Not Satisfiable");
        MESSAGES.put(417, "Expectation Failed");
        MESSAGES.put(418, "I'm a Teapot");
        MESSAGES.put(421, "Misdirected Request");
        MESSAGES.put(422, "Unprocessable Entity");
        MESSAGES.put(423, "Locked");
        MESSAGES.put(424, "Failed Dependency");
        MESSAGES.put(426, "Upgrade Required");
        MESSAGES.put(428, "Precondition Required");
        MESSAGES.put(429, "Too Many Requests");
        MESSAGES.put(431, "Request Header Fields Too Large");
        MESSAGES.put(451, "Unavailable For Legal Reasons");

        // 5xx Server Errors
        MESSAGES.put(500, "Internal Server Error");
        MESSAGES.put(501, "Not Implemented");
        MESSAGES.put(502, "Bad Gateway");
        MESSAGES.put(503, "Service Unavailable");
        MESSAGES.put(504, "Gateway Timeout");
        MESSAGES.put(505, "HTTP Version Not Supported");
        MESSAGES.put(506, "Variant Also Negotiates");
        MESSAGES.put(507, "Insufficient Storage");
        MESSAGES.put(508, "Loop Detected");
        MESSAGES.put(510, "Not Extended");
        MESSAGES.put(511, "Network Authentication Required");
    }

    public static String of(final int status) {
        return MESSAGES.getOrDefault(status, null);
    }
}
