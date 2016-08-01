package com.example.bbi_w1003.classicview;

/**
 * Created by BBI-W 1003 on 7/26/2016.
 */
public class ClassicDataHolder {

    /**
     * The Name.
     */
    String name;
    /**
     * The Html.
     */
    String Html;

    /**
     * Instantiates a new ClassicDataHolder.
     *
     * @param name the name
     * @param html the html
     */
    public ClassicDataHolder(String name, String html) {
        this.name = name;
        Html = html;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets html.
     *
     * @return the html
     */
    public String getHtml() {
        return Html;
    }

    /**
     * Sets html.
     *
     * @param html the html
     */
    public void setHtml(String html) {
        Html = html;
    }
}
