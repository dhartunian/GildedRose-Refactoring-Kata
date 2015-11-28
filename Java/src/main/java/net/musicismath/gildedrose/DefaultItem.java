package net.musicismath.gildedrose;

public class DefaultItem extends Item implements UpdateableQuality {
    public DefaultItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.sellIn--;
        if (this.sellIn < 0) {
            this.quality = Math.max(this.quality - 2, 0);
        } else {
            this.quality = Math.max(this.quality - 1, 0);
        }
    }
}
