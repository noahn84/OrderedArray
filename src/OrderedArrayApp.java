class OrderedArray {
  // initial data
  private long[] arr; // ref to array a
  private int nElms; // number of data items

  // constructor
  public OrderedArray(int max) {
    arr = new long[max];
    nElms = 0;
  }

  // methods
  public int size() {
    return nElms;
  }

  public int find(long key) {
    int left = 0; // left pointer
    int right = nElms - 1; // right pointer
    int idx; // ref to index

    while (true) {
      idx = (left + right) / 2; // set index to middle of the range
      if (arr[idx] == key) {
        return idx; // found index of key
      } else if (left > right) {
        return nElms; // entire array has been traversed
      } else {
        if (arr[idx] < key) { // current value is smaller than key
          left = idx + 1; // increase index by 1 to get greater value
        } else {
          right = idx - 1; // decrease index by 1 to get smaller value
        }
      }
    }
  }

  public void insert(long value) {
    int idx; // ref to index
    for (idx = 0; idx < nElms; idx++) { // find where value fits
      if (arr[idx] > value) { // (linear search) 'if (0 > value)....'
        break;
      }
    }
    for (int jdx = nElms; jdx > idx; jdx--) { // move bigger values up
      arr[jdx] = arr[jdx - 1];
    }
    arr[idx] = value; // insert value
    nElms++; // increment size
  }

  public boolean delete(long value) {
    int target = find(value);
    if (target == nElms) { // can't find target
      return false;
    } else { // found target
      for (int idx = target; idx < nElms; idx++) {
        arr[idx] = arr[idx + 1]; // removing value, moving greater values down
      }
      nElms--; // decrement size
      return true;
    }
  }

  public void display() {
    for (int idx = 0; idx < nElms; idx++) {
      System.out.print(arr[idx] + " ");
    }
    System.out.println("");
  }
}

class OrderedArrayApp {
  public static void main(String[] args) {
    int maxSize = 100;
    OrderedArray arr;
    arr = new OrderedArray(maxSize);

    arr.insert(55);
    arr.insert(22);
    arr.insert(77);
    arr.insert(44);
    arr.insert(66);
    arr.insert(11);
    arr.insert(88);
    arr.insert(33);

    arr.display();

    int key = 55;
    if (arr.find(key) != arr.size()) {
      System.out.println("Found " + key);
    } else {
      System.out.println("Can't find " + key);
    }

    System.out.println("Removing " + key);

    arr.delete(key);

    arr.display();
  }
}