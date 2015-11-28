package net.musicismath.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("foo");
    }

    @Test
    public void updateQuality_LowersSellInAndQuality() {
        Item item = new Item("foo", 10, 20);
        GildedRose app = wrapInApp(item);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(9);
        assertThat(app.items[0].quality).isEqualTo(19);
    }

    @Test
    public void updateQuality_afterSellInQualityDegradesTwiceAsFast() {
        Item item = new Item("foo", 2, 20);
        GildedRose app = wrapInApp(item);

        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(19);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(18);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(16);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(14);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(12);
    }

    @Test
    public void updateQuality_neverNegative() {
        Item item = new Item("foo", 2, 1);
        GildedRose app = wrapInApp(item);

        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(0);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(0);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(0);
    }

    private Item getSingleItem(GildedRose app) {
        return app.items[0];
    }

    private GildedRose wrapInApp(Item item) {
        return new GildedRose(new Item[] {item});
    }

}
