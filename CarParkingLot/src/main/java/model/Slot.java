package model;

/**
 * Created by thej on 3/10/16.
 */
public class Slot {
    int slotNo;
    boolean isOccupied;

    public Slot() {
    }

    public Slot(int i, boolean b) {
        this.slotNo = i;
        this.isOccupied = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot slot = (Slot) o;

        if (slotNo != slot.slotNo) return false;
        return isOccupied == slot.isOccupied;

    }

    @Override
    public int hashCode() {
        int result = slotNo;
        result = 31 * result + (isOccupied ? 1 : 0);
        return result;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
