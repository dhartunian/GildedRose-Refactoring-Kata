package net.musicismath.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item item = new Item("foo", 0, 0);
        assertThat(item.name).isEqualTo("foo");
    }

    @Test
    public void updateQuality_LowersSellInAndQuality() {
        Item item = new Item("foo", 10, 20);
        item.updateQuality();
        assertThat(item.sellIn).isEqualTo(9);
        assertThat(item.quality).isEqualTo(19);
    }

    @Test
    public void updateQuality_isExpired() {
        Item item = new Item("foo", 1, 20);
        assertThat(item.isExpired()).isFalse();
        item.updateQuality();
        assertThat(item.isExpired()).isFalse();
        item.updateQuality();
        assertThat(item.isExpired()).isTrue();
    }

    @Test
    public void updateQuality_brieIncreases() {
        Item brie = Item.brie(10, 20);
        brie.updateQuality();
        assertThat(brie.quality).isEqualTo(21);
    }

    @Test
    public void updateQuality_brieLimit50() {
        Item brie = Item.brie(10, 50);
        brie.updateQuality();
        assertThat(brie.quality).isEqualTo(50);
    }
}
