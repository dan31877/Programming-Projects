/*
 * Daniel Anderson
 * CS 472  11/7/2013
 * Project 2: Cache Simulator
 */

#include <iostream>
#include <string>

using namespace std;

/* Prototypes */
void populateMM(short mm[], int mmSize);
void populateCache(short cache[][20]);
void displayCache(short cache[][20]);
void processAddress(short address, short addressArray[]);
void copyAddresstoCache(short addr[], short cache[][20], short mm[]);

/* Main Method*/
int main()
{

int mmSize = 2048;
short mainMem[mmSize];
short addressArray[4];
bool quit = false;

populateMM(mainMem, mmSize);

short cache[16][20];
populateCache(cache);
string direction;

/* The main code that runs the cache simulation */
do{

    cout << "(R)ead, (W)rite, or (D)isplay Cache? [(Q) to quit]" << endl;
    cin >> direction;

    // Read section
    if( direction.compare("R") == 0 || direction.compare("r") == 0){

        short addr, slot, tag, place, retValue;
        string hitOrMiss;

        cout << "What address would you like read?" << endl;  // Read in the address
        cin >> hex >> addr;

        processAddress(addr, addressArray);  // Process the address
        slot = addressArray[0];
        tag = addressArray[1];
        place = (addressArray[2] & 0x0000000F) + 4; // Finds the right spot in the cache array to get the value

        // Determines if the value is in the cache or not
        if( cache[slot][2] == 1 && cache[slot][3] == tag){
            hitOrMiss = " (Cache Hit)";
        }
        else{
            hitOrMiss = " (Cache Miss)";
            copyAddresstoCache(addressArray, cache, mainMem);
        }

        // Returns the value from the cache
        retValue = cache[slot][place];
        cout << "At that byte there is the value " << hex << retValue << hitOrMiss << "\n" <<endl;
        //displayCache(cache);

    }

    // Write section
    else if( direction.compare("W") == 0 || direction.compare("w") == 0){

        short addr, slot, tag, place, retValue, data;
        string hitOrMiss;

        cout << "What address would you like write to?" << endl;  // Read in the address
        cin >> hex >> addr;

        cout << "What data would you like to write at that address?" << endl;
        cin >> hex >> data;

        processAddress(addr, addressArray);  // Process the address
        slot = addressArray[0];
        tag = addressArray[1];
        place = (addressArray[2] & 0x0000000F) + 4; // Finds the right spot in the cache array to get the value

        // Determines if the value is in the cache or not
        if( cache[slot][2] == 1 && cache[slot][3] == tag){
            hitOrMiss = " (Cache Hit)";
        }
        else{
            hitOrMiss = " (Cache Miss)";
            copyAddresstoCache(addressArray, cache, mainMem);
        }

        // Returns the value from the cache
        cache[slot][place] = data;
        cache[slot][1] = 1;
        cout << "Value " << data << " has been written to address " << addr << hitOrMiss << "\n" << endl;

    }

    // Display the cache
    else if( direction.compare("D") == 0 || direction.compare("d") == 0){
        displayCache(cache);
    }

    // Quit the simulation
    else if( direction.compare("Q") == 0 || direction.compare("q") == 0){
         quit = true;
    }

    // Checks if the user types the wrong key
    else{
        cout << "Please choose a valid entry." << endl;
    }
} while(quit == false);

}

/* Populates the values of the Main Memory array with hex 0-F corresponding to the address*/
void populateMM(short mm[], int mmSize){

    short counter = 0;
    for(int i=0; i<mmSize; i++){

        mm[i] = counter;
        if( counter == 0xFF)
            counter = 0;
        else
            counter++;

    }

}

/* Populates the cache array initially with slot numbers and zeroes for all other values */
void populateCache(short cache[][20]){

    for( short i=0; i<16; i++){
        for( int j=0; j<20; j++){
                if(j==0)
                    cache[i][j] = i;
                else
                    cache[i][j] = 0;
        }
    }
}

/* Processes the address to find slot number, tag, offset, and beginning address. Puts values into an array*/
void processAddress(short address, short addressArray[]){

    // Zeros out offset and tag, then moves to find slot number
    short slotNum = (address & 0x000000F0) >> 4;

    short tag = address >> 8;          // Moves the address to find the tag
    short offset = address & 0x000000FF;   // The last 8 bits of the address
    short beginAddr = address & 0xFFFFFFF0;  // The beginning of the block

    // Add to address array
    addressArray[0] = slotNum;
    addressArray[1] = tag;
    addressArray[2] = offset;
    addressArray[3] = beginAddr;

}

/* Copies the block from Main Memory to the Cache. Also, determines the slot, copies the tag. */
void copyAddresstoCache(short addr[], short cache[][20], short mm[]){

    // Writes back to main memory
    short row = addr[0];
    short tag = cache [row][3];  // Old Tag, to write to

    // Checks the dirty bit, then writes back to MM if the dirty bit is 1
    if(cache[row][1] == 1){
        short address = tag << 8;
        int col = 4;
        address += cache[row][col];

        for(int i=0; i<16; i++){
            mm[address] = cache[row][col++];
            address++;
        }
        cache[row][1] = 0;
    }

    cache[row][2] = 1;
    cache[row][3] = addr[1];

    short start = addr[3];   // Beginning address of the block
    int col = 4;

    for(int i=0; i<16; i++){
        cache[row][col++] = mm[start++];
    }

}

/* Displays the contents of the cache array. */
void displayCache(short cache[][20]){

    bool isValid = false;
    cout << "Slot  Dirty  Valid  Tag      Data" << endl;
    for(int i=0; i<16; i++){
        for( int j=0; j<20; j++){
                switch(j){
                case 0:
                    {
                        cout << " " << hex << cache[i][j] << "   ";
                        break;
                    }
                case 1:
                    {
                        cout << "  " << hex << cache[i][j] << "     ";
                        break;
                    }
                case 2:
                    {
                        cout << "  " << hex << cache[i][j] << "     ";
                        break;
                    }
                case 3:
                    {
                        cout << " " << hex << cache[i][j] << "        ";
                        break;
                    }
                default:
                    {
                        if(cache[i][j] >= 0xf)    // For even spacing
                            cout << hex << cache[i][j] << " ";
                        else
                            cout << hex << cache[i][j] << "  ";
                        break;
                    }
                }
        }
        cout << "\n" << endl;
    }
}
