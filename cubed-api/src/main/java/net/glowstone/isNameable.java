package net.glowstone;

/**
 * Used if an object can be named. Will provided a setter and getter for said name.
 *
 * @author jdesive
 */
public interface isNameable {

    /**
     * Returns the custom name if set.
     *
     * @return {@link String} Custom Name
     */
    String getCustomName();


    /**
     * Sets the custom name.
     *
     * @param customName {@link String} To set as a custom name
      */
    void setCustomName(String customName);

}
