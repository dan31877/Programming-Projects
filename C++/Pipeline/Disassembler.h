#ifndef DISASSEMBLER_H
#define DISASSEMBLER_H
#include <iostream>
#include <sstream>
#include <string>

class Disassembler
{

private:
public:
Disassembler(int addr)
{
    /*int instructionSize = 11;
    int instruction[11] = {0x022DA822, 0x8EF30018, 0x12A70004, 0x02689820, 0xAD930018, 0x02697824, 0xAD8FFFF4,
    0x018C6020, 0x02A4A825, 0x158FFFF6, 0x8E59FFF0};*/


    address = addr;
    resultArray[5];
}

std::string runDisassembler(){
    //for(int i=0; i<instructionSize; i++){
        getOpCodeAndTwoReg(resultArray, address);
            if(resultArray[0] == 0)
                getRFormat(resultArray, address);
            else
                getIFormat(resultArray, address);

        //std::cout << resultArray[0] << " " << resultArray[1] << " " << resultArray[2] << " " << resultArray[3] << " " << resultArray[4] << " " << std::endl;
        return displayResult(resultArray, address);
        //address += 4;
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

    ret[4] = applyMaskAndShift(instruct, functionMask, 0);
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
std::string displayResult(int x[], int address){

    int fncn = x[0];
    int reg1 = x[1];
    int opCode = x[4];
    std::stringstream stream;
    std::string result;

    switch(fncn){

        case 0:
            {
             switch(opCode){
                case 32:
                    {
                         stream << "add $" << x[3] << ", $" << x[1] << ", $" << x[2];
                         result = stream.str();
                         break;
                    }
                case 34:
                    {
                         stream << "sub $" << x[3] << ", $" << x[1] << ", $" << x[2];
                         result = stream.str();
                         break;
                    }
                default:
                    {
                        result = "The op code or function you're using is not valid.";
                        break;
                    }
                }
            break;
            }
        case 32:
            {
                 stream << "lb $" << x[2] << ", " << x[3] << " ($" << x[1] << ")";
                 result = stream.str();
                 break;
            }
        case 40:
            {
                 stream << "sb $" << x[2] << ", " << x[3] << " ($" << x[1] << ")";

                 result = stream.str();
                 break;
            }
        default:
            {
                result =  "The op code or function you're using is not valid.";
                break;
            }
    }
    return result;
}

}
