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
        assertThat(getSingleItem(app).quality).isEqualTo(16); //after sell-by
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
        assertThat(getSingleItem(app).quality).isEqualTo(0); //after sell-by
    }

    @Test
    public void updateQuality_agedBrieIncreases() {
        Item item = new Item(AgedBrie.BRIE, 2, 1);
        GildedRose app = wrapInApp(item);

        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(2);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(3);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(5); //after sell-by
    }

    @Test
    public void updateQuality_neverMoreThan50() {
        Item item = new Item(AgedBrie.BRIE, 2, 49);
        GildedRose app = wrapInApp(item);

        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(50);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(50);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(50); //after sell-by
    }

    @Test
    public void updateQuality_sulfurasNeverDecreases() {
        Item item = new Item(Sulfuras.SULFURAS, 2, 80);
        GildedRose app = wrapInApp(item);

        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(80);
        assertThat(getSingleItem(app).sellIn).isEqualTo(2);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(80);
        assertThat(getSingleItem(app).sellIn).isEqualTo(2);
        app.updateQuality();
        assertThat(getSingleItem(app).quality).isEqualTo(80); //after sell-by
        assertThat(getSingleItem(app).sellIn).isEqualTo(2);
    }

    @Test
    public void updateQuality_backstageIncreases() {
        Item item = new Item(BackstagePasses.BACKSTAGE, 15, 10);
        GildedRose app = wrapInApp(item);

        assertUpdatesToSellInAndQuality(app,
                                        14, 11,
                                        13, 12,
                                        12, 13,
                                        11, 14,
                                        10, 15,
                                        9, 17,
                                        8, 19,
                                        7, 21,
                                        6, 23,
                                        5, 25,
                                        4, 28,
                                        3, 31,
                                        2, 34,
                                        1, 37,
                                        0, 40,
                                        -1, 0,
                                        -2, 0);
    }

    private void assertUpdatesToSellInAndQuality(GildedRose app, int... expected) {
        for (int i = 0; i < expected.length; i+=2) {
            app.updateQuality();
            assertThat(getSingleItem(app).sellIn).isEqualTo(expected[i]);
            assertThat(getSingleItem(app).quality).isEqualTo(expected[i+1]);
        }
    }

    private Item getSingleItem(GildedRose app) {
        return app.items[0];
    }

    private GildedRose wrapInApp(Item item) {
        return new GildedRose(new Item[] {item});
    }

}
