import java.util.Arrays;
import java.util.Comparator;

public class Backpack {
    //cuttable
    public double maxValueC(StealItem[] items, double v) {
        if (items == null || v < 0) throw new IllegalArgumentException();
        Arrays.sort(items, new Comparator<StealItem>() {
            @Override
            public int compare(StealItem a, StealItem b) {
                double unitValueB = b.value / b.volume;
                double unitValueA = a.value / a.volume;
                if (unitValueA < unitValueB) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        double totalValue = 0;
        int i = 0;
        while (i < items.length && v - items[i].volume > 0) {
            v-= items[i].volume;
            totalValue += items[i].value;
            i++;
        }

        if (v > 0 && i < items.length) {
            double partial = items[i].value / items[i].volume * v;
            totalValue += partial;
        }
        return totalValue;
    }

    public static void main(String args[]) {
        Backpack test = new Backpack();
        StealItem[] items1 = new StealItem[] {
                new StealItem(2, 5),
                new StealItem(3, 5),
                new StealItem(6, 12),
                new StealItem(1, 200)
        };
        System.out.println("items 1: " + test.maxValueC(items1, 20));
    }
}

class StealItem {
    double value;
    double volume;

    StealItem(double value, double volume) {
        this.value = value;
        this.volume = volume;
    }
}
