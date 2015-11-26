package net.musicismath.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public String name;
    public int sellIn;
    public int quality;

    public Boolean isExpired() {
        return sellIn < 0;
    }

    public static Item brie(int sellIn, int quality) {
        return new Item(AGED_BRIE, sellIn, quality);
    }

    public static Item backstagePasses(int sellIn, int quality) {
        return new Item(BACKSTAGE_PASSES, sellIn, quality);
    }

    public static Item sulfuras(int sellIn, int quality) {
        return new Item(SULFURAS, sellIn, quality);
    }

    public void updateQuality() {
        if (isBrie()
            && !isBackstagePasses()) {
            if (quality > 0) {
                if (!isSulfuras()) {
                    quality = quality - 1;
                }
            }
        } else {
            if (quality < 50) {
                quality = quality + 1;

                if (isBackstagePasses()) {
                    if (sellIn < 11) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }

                    if (sellIn < 6) {
                        if (quality < 50) {
                            quality = quality + 1;
                        }
                    }
                }
            }
        }

        if (!isSulfuras()) {
            sellIn = sellIn - 1;
        }

        if (sellIn < 0) {
            if (!isBrie()) {
                if (!isBackstagePasses()) {
                    if (quality > 0) {
                        if (!isSulfuras()) {
                            quality = quality - 1;
                        }
                    }
                } else {
                    quality = quality - quality;
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }

    private boolean isSulfuras() {
        return name.equals(SULFURAS);
    }

    private boolean isBackstagePasses() {
        return name.equals(BACKSTAGE_PASSES);
    }

    private boolean isBrie() {
        return !name.equals(AGED_BRIE);
    }

}
