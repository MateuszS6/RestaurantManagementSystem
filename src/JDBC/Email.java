package JDBC;

/**
 * Represents an email.
 */
public class Email {
    /**
     * The sender of the email.
     */
    private String sender;

    /**
     * The recipient of the email.
     */
    private String recipient;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The message content of the email.
     */
    private String message;

    /**
     * Constructs an Email object with the given sender, recipient, subject, and message.
     *
     * @param sender    the sender of the email
     * @param recipient the recipient of the email
     * @param subject   the subject of the email
     * @param message   the message content of the email
     */
    public Email(String sender, String recipient, String subject, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    /**
     * Gets the sender of the email.
     *
     * @return the sender of the email
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the sender of the email.
     *
     * @param sender the sender of the email
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets the recipient of the email.
     *
     * @return the recipient of the email
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the recipient of the email.
     *
     * @param recipient the recipient of the email
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Gets the subject of the email.
     *
     * @return the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject the subject of the email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the message content of the email.
     *
     * @return the message content of the email
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message content of the email.
     *
     * @param message the message content of the email
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
