package com.team.eventbook;

public class post_view {
    private int id;
    private String titile;
    public post_view(String description,String titile,String datefor,String dateto,String timefor,String timeto
             )
    {
        this.id=id;
        this.titile=titile;
        this.description=description;
        this.datefor=datefor;
        this.dateto=dateto;
        this.timefor=timefor;
        this.timeto=timeto;
        this.repeatation=repeatation;
        this.fee=fee;
        this.venue=venue;
        this.categories=categories;
        this.Partner=Partner;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatefor() {
        return datefor;
    }

    public void setDatefor(String datefor) {
        this.datefor = datefor;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getTimefor() {
        return timefor;
    }

    public void setTimefor(String timefor) {
        this.timefor = timefor;
    }

    public String getTimeto() {
        return timeto;
    }

    public void setTimeto(String timeto) {
        this.timeto = timeto;
    }

    public String getRepeatation() {
        return repeatation;
    }

    public void setRepeatation(String repeatation) {
        this.repeatation = repeatation;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPartner() {
        return Partner;
    }

    public void setPartner(String partner) {
        Partner = partner;
    }

    private String description;
    private String datefor;
    private String dateto;
    private String timefor;
    private String timeto;
    private String repeatation;
    private String fee;
    private String venue;
    private String categories;
    private String Partner;
}
