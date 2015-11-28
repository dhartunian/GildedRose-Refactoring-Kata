package net.musicismath.gildedrose;

public class Sulfuras extends Item implements UpdateableQuality {
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public Sulfuras(int sellIn, int quality) {
        super(SULFURAS, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        //noop
    }
}
