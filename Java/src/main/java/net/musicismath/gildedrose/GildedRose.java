package net.musicismath.gildedrose;

class GildedRose {
    static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    static final String BRIE = "Aged Brie";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQualityBrie(Item item) {
        assert item.name.equals(BRIE);
        item.sellIn--;
        if (item.sellIn < 0 ) {
            item.quality = Math.min(item.quality + 2, 50);
        } else {
            item.quality = Math.min(item.quality + 1, 50);
        }
    }

    private void updateQualityBackstagePasses(Item item) {
        assert item.name.equals(BACKSTAGE);
        item.sellIn--;
        if (item.sellIn < 10 && item.sellIn >= 5) {
            item.quality = Math.min(item.quality + 2, 50);
        } else if (item.sellIn < 5 && item.sellIn >= 0) {
            item.quality = Math.min(item.quality + 3, 50);
        } else if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            item.quality = Math.min(item.quality + 1 , 50);
        }
    }

    private void updateQualitySulfuras(Item item) {
        assert item.name.equals(SULFURAS);
    }

    private void updateQualityDefault(Item item) {
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = Math.max(item.quality - 2, 0);
        } else {
            item.quality = Math.max(item.quality - 1, 0);
        }
    }

    private void updateQuality(Item item) {
        switch (item.name) {
            case SULFURAS:
                updateQualitySulfuras(item);
                break;
            case BRIE:
                updateQualityBrie(item);
                break;
            case BACKSTAGE:
                updateQualityBackstagePasses(item);
                break;
            default:
                updateQualityDefault(item);
        }
    }

}
