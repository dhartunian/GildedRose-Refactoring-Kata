package net.musicismath.gildedrose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
        }
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
        if (item instanceof UpdateableQuality) {
            ((UpdateableQuality) item).updateQuality();
        } else {
            updateQualityDefault(item);
        }
    }

}
