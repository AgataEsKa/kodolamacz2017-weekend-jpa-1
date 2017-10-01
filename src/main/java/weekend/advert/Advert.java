package weekend.advert;

import javax.persistence.*;

@Entity
public class Advert extends AbstractEntity {

    private String title;
    private String text;
    @Column(nullable = false, columnDefinition = "int default 0")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    // my nie używamy tego konstruktora, ale hibernate tak
    // dlatego musi tutaj być
    public Advert() {}

    public Advert(String title, String text, int price) {
        this(title, text, price, Category.OTHER);
    }

    public Advert(String title, String text, int price, Category category) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category=" + category +
                "} " + super.toString();
    }
}
