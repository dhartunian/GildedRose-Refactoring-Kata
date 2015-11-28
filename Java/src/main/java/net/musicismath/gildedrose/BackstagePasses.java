package net.musicismath.gildedrose;

public class BackstagePasses extends Item implements UpdateableQuality {
    static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";

    public BackstagePasses(int sellIn, int quality) {
        super(BACKSTAGE, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.sellIn--;
        if (this.sellIn < 10 && this.sellIn >= 5) {
            this.quality = Math.min(this.quality + 2, 50);
        } else if (this.sellIn < 5 && this.sellIn >= 0) {
            this.quality = Math.min(this.quality + 3, 50);
        } else if (this.sellIn < 0) {
            this.quality = 0;
        } else {
            this.quality = Math.min(this.quality + 1 , 50);
        }
    }
}
