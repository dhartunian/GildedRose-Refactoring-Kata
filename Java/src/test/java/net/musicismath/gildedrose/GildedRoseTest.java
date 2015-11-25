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

}
