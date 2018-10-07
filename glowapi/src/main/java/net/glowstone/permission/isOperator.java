package net.glowstone.permission;

/**
 * Used by {@link isPermissible} to allow bypassing all permissions
 *
 * @author jdesive
 */
public interface isOperator {

    /**
     * Returns if all permissions are bypassed or not.
     *
     * @return {@link Boolean} True if permissions are bypassed
     */
    boolean isOperator();

    /**
     * Sets if all permissions are bypassed.
     *
     * @param op {@link Boolean} True if permissions are bypassed
     */
    void setOperator(boolean op);

}
