/*
 * Daniel Anderson
 * CS 472  10/3/2013
 * Project 1: MIPS Disassembler
 */

#include <iostream>

using namespace std;

/* Prototypes */
int applyMaskAndShift(int instruct, int mask, int shifter);
void getOpCodeAndTwoReg(int ret[], int instruct);
void getRFormat(int ret[], int instruct);
void getIFormat(int ret[], int instruct);
int branchFindAddress(int address, int offset);
void displayResult(int x[], int address);

/* Main Method */
int main()
{
    int instructionSize = 11;
    int instruction[11] = {0x022DA822, 0x8EF30018, 0x12A70004, 0x02689820, 0xAD930018, 0x02697824, 0xAD8FFFF4,
    0x018C6020, 0x02A4A825, 0x158FFFF6, 0x8E59FFF0};


    int address = 0x7A060;
    int resultArray[4];

    for(int i=0; i<instructionSize; i++){
        getOpCodeAndTwoReg(resultArray, instruction[i]);
            if(resultArray[0] == 0)
                getRFormat(resultArray, instruction[i]);
            else
                getIFormat(resultArray, instruction[i]);

        displayResult(resultArray, address);
        address += 4;
    }

    /* Test branch tricks from 9A000 to 9A02C
    int address = 0x0009A000;
    int offset = -10;
    int offsetTest = branchFindAddress(address, offset);
    std::cout << std::hex << std::uppercase <<offsetTest << std::endl;*/

}

/*
 * getOpCodeAndTwoReg - Gets the Operation Code and the first two registers of the instruction and
 * assigns to the first elements in the array.
 */
void getOpCodeAndTwoReg(int ret[], int instruct){

    int opCodeMask = 0xFC000000;
    int firstRegMask = 0x03E00000;
    int secondRegMask = 0x001F0000;

    int opShift = 26;
    int firstShift = 21;
    int secondShift = 16;

    ret[0] = applyMaskAndShift(instruct, opCodeMask, opShift);
    ret[1] = applyMaskAndShift(instruct, firstRegMask, firstShift);
    ret[2] = applyMaskAndShift(instruct, secondRegMask, secondShift);

    // << opCode << " " << first << " " << second << endl;  //for testing
}

/*
 * getRFormat - Finds the third register and the function code for instructions that are R-Format.
 */
void getRFormat(int ret[], int instruct){

    int thirdRegMask = 0x0000F800;
    int functionMask = 0x0000003F;

    int thirdShift = 11;

    ret[0] = applyMaskAndShift(instruct, functionMask, 0);
    ret[3] = applyMaskAndShift(instruct, thirdRegMask, thirdShift);

}

/*
 * getIFormat - Gets the offset for I Format instructions.
 */
void getIFormat(int ret[], int instruct){

    int mask = 0x0000FFFF;
    short offset = instruct & mask;

    ret[3] = (int)offset;

}

/*
 * applyMaskAndShift - Applies the mask and shifts the portion of the instruction to the appropriate point.
 */
int applyMaskAndShift(int instruct, int mask, int shifter){

    unsigned int retValue = instruct & mask;
    retValue = retValue >> shifter;
    return retValue;

}

/*
 * branchFindAddress - Uses the offset and the branch rules to find the label address.
 */
int branchFindAddress(int address, int offset){
    offset = offset << 2;
    offset += 4;
    return (address + offset);

}

/*
 * displayResult - Displays the contents of the array according to their specific operation code or function code
 */
void displayResult(int x[], int address){

    int fncn = x[0];
    int reg1 = x[1];

    switch(fncn){

        case 32:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  add $" << x[3] << ", $" << x[1] << ", $" << x[2] << endl;
                 break;
            }
        case 34:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  sub $" << x[3] << ", $" << x[1] << ", $" << x[2]  << endl;
                 break;
            }
        case 36:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  and $" << x[3] << ", $" << x[1] << ", $" << x[2]  << endl;
                 break;
            }
        case 37:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  or $" << x[3] << ", $" << x[1] << ", $" << x[2] << endl;
                 break;
            }
        case 42:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  slt $" << x[3] << ", $" << x[1] << ", $" << x[2]  << endl;
                 break;
            }
        case 35:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  lw $" << x[2] << ", " << x[3] << ", ($" << x[1] << ")" << endl;
                 break;
            }
        case 43:
            {
                 cout << hex << uppercase <<address;
                 cout << dec << "  sw $" << x[2] << ", " << x[3] << ", ($" << x[1] << ")" << endl;
                 break;
            }
        case 4:
            {
                 int label = branchFindAddress(address, x[3]);
                 cout << hex << uppercase <<address;
                 cout << dec << "  beq $" << x[1] << ", $" << x[2];
                 cout << hex << ", address " << label << endl;
                 break;
            }
        case 5:
            {
                 int label = branchFindAddress(address, x[3]);
                 cout << hex << uppercase <<address;
                 cout << dec << "  bne $" << x[1] << ", $" << x[2];
                 cout << hex << ", address " << label  << endl;
                 break;
            }
        default:
            {
                cout << "The op code or function you're using is not valid." << endl;
                break;
            }
    }
}
