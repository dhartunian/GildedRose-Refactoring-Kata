package net.musicismath.gildedrose;

public class AgedBrie extends Item implements UpdateableQuality {
    static final String BRIE = "Aged Brie";

    public AgedBrie(int sellIn, int quality) {
        super(BRIE, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.sellIn--;
        if (this.sellIn < 0 ) {
            this.quality = Math.min(this.quality + 2, 50);
        } else {
            this.quality = Math.min(this.quality + 1, 50);
        }
    }
}
