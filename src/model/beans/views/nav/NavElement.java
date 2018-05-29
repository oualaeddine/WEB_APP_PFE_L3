package model.beans.views.nav;

import model.beans.views.MyView;

public class NavElement extends MyView {
    private boolean isPage;
    private String
            title;
    private String url;
    private String icon;

    public NavElement(boolean isPage, String title, String url, String icon) {
        this.isPage = isPage;
        this.title = title;
        this.url = url;
        this.icon = icon;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml() {
        return "            <li class=\"" + getCssClass() + "\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"" + getTitle() + "\">\n" +
                "                <a class=\"nav-link\" href=\"" + getUrl() + "\">\n" +
                "                    <i class=\"fa fa-fw " + getIcon() + "\"></i>\n" +
                "                    <span class=\"nav-link-text\">" + getTitle() + "</span>\n" +
                "                </a>\n" +
                "            </li>";
    }

    private String getCssClass() {
        if (isPage)
            return " nav-item nav-item-selected";
        else
            return " nav-item ";
    }

    public String getIcon() {
        return icon;
    }
}
