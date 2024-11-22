/***
 * Initializes an empty dynamic array with an initial capacity of 100.
 * Owner : Avadhi-Singhal
 * Date of Creation : 21/11/2024
 */

class ValueArray{
        private int[] values;
        private int size;

        public ValueArray() {
            this.values = new int[100];
            this.size = 0;
        }

        /***
         * Adds a value to the array, resizing if necessary
         * @param value The value to add.
         */
        public void add(int value) {
            if (size == values.length) {
                int[] newValues = new int[values.length * 2];
                for (int i = 0; i < values.length; i++) {
                    newValues[i] = values[i];
                }
                values = newValues;
            }
            values[size++] = value;
        }

        /***
         * Retrieves the value at a specified index.
         * @param index Index to retrieve the value from.
         * @return Value at the specified index.
         * @throws IndexOutOfBoundsException If index is invalid.
         */
        public int get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds: " + index);
            }
            return values[index];
        }

        /***
         * Returns the current number of elements in the array.
         * @return Number of elements in the array.
         */
        public int size() {
            return size;
        }
    }