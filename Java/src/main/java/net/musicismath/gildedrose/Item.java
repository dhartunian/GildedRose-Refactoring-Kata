package net.musicismath.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    public String name;
    public int sellIn;
    public int quality;

}
