/*
 * Daniel Anderson
 * CS 472  12/5/2013
 * Project 3: Pipeline
 */

#include <iostream>
#include <string>
#include <sstream>
#include <iomanip>

// Always copy. Initialize Opfcn to NOP, check print and write logic using NOP. If nop in write set Opfcn to NOP & Set writes 0

using namespace std;

/* Prototypes and Variables*/
void IF_stage();
void ID_stage();
void EX_stage();
void MEM_stage();
void WB_stage();
void Print_out_everything();
void Copy_write_to_read();
void populateMM();
void populateREG();
void printREG();
void printMM();

short Main_Mem[1024];
int Regs[32];
int instr;

/* Struct IFID - holds the IFID variables*/
struct IFID{
    bool noOp = true;
    int instruction;
    string instr_disassembled;
    int disArray[5];

};

/* Struct IDEX - holds the IDEX variables*/
struct IDEX{
    bool noOp = true;
    int RegDst = 0;
    int ALUSrc = 0;
    int ALUOp = 0;
    int MemRead = 0;
    int MemWrite = 0;
    int MemToReg = 0;
    int RegWrite = 0;
    int ReadReg1Value = 0;
    int ReadReg2Value = 0;
    int SEOffset = 0;
    int WriteReg_20_16 = 0;
    int WriteReg_15_11 = 0;
    int Function = 0;
    string Opfcn = "nop";

};

/* Struct EXMEM - holds the EXMEM variables*/
struct EXMEM{
    bool noOp = true;
    int MemRead = 0;
    int MemWrite = 0;
    int MemToReg = 0;
    int RegWrite = 0;
    int ALUResult = 0;
    int SBValue = 0;
    int WriteRegNum = 0;
    string Opfcn = "nop";

};

/* Struct MEMWB - holds the MEMWB variables*/
struct MEMWB{
    bool noOp = true;
    int MemToReg = 0;
    int RegWrite = 0;
    int LBDataValue = 0;
    int ALUResult = 0;
    int WriteRegNum = 0;
    string Opfcn = "nop";
    string outStatement;
};

class Disassembler
{

private:
public:
    int address;
    int resultArray[5];
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

};


/* Write and Read instances for Pipeline structs*/
struct IFID ifid_write;
struct IFID ifid_read;

struct IDEX idex_write;
struct IDEX idex_read;

struct EXMEM exmem_write;
struct EXMEM exmem_read;

struct MEMWB memwb_write;
struct MEMWB memwb_read;

// Main Method
int main()
{
    // Populate Main Memory and Register Arrays
    populateMM();
    populateREG();

    int InstructionCache[12] = {0xa1020000, 0x810AFFFC, 0x00831820, 0x01263820, 0x01224820,
                    0x81180000, 0x81510010, 0x00624022, 0x00000000, 0x00000000, 0x00000000, 0x00000000};



    // Runs the methods in the correct order
    for(int i=0; i<12; i++){

        instr = InstructionCache[i];
        //cout << hex << instr << endl;
        cout << "Clock Cycle " << dec << i+1 << " (Before we copy the write side of pipeline registers to the read side)" << endl;

        IF_stage();
        ID_stage();
        EX_stage();
        MEM_stage();
        WB_stage();
        Print_out_everything();
        Copy_write_to_read();
    }

    // Print the main memory
    printMM();

    /*Disassembler dis(0xa1020000);
    string thisOne = dis.runDisassembler();
    int thisArray[5];
    for(int i = 0; i<5; i++)
        thisArray[i] = dis.resultArray[i];
    cout << thisOne << endl;
    //int zero = dis.resultArray[0];
    //cout << zero << endl;
    cout << thisArray[0] << " " << thisArray[1] << " " << thisArray[2] << " " << thisArray[3] << " " << thisArray[4] << " " << endl;

    if (thisOne.compare(0,2,"sb") == 0)
        cout << "This is a store." << endl;

    if (thisOne.compare(0,2,"lb") == 0)
        cout << "This is a load" << endl;

    if (thisOne.compare(0,2,"ad") == 0)
        cout << "This is an add." << endl;

    if (thisOne.compare(0,2,"su") == 0)
        cout << "This is a subtract" << endl;

    //populateREG();
    //printREG();

    //Copy_write_to_read();
    //Print_out_everything();
    */
    return 0;
}

/* Fetches the next instruction out of the Instruction Cache */
void IF_stage(){


    if(instr != 0){
        ifid_write.noOp = false;
        ifid_write.instruction = instr;
        Disassembler dis(ifid_write.instruction);
        ifid_write.instr_disassembled = dis.runDisassembler();
        for(int i = 0; i<5; i++)
            ifid_write.disArray[i] = dis.resultArray[i];
    }
    else{
        ifid_write.instruction = 0;
        ifid_write.noOp = true;
    }
}

/* Reads an instruction from the READ version of IF/ID pipeline register */
void ID_stage(){


    if( !ifid_read.noOp){
        idex_write.noOp = false;
        if(ifid_read.disArray[0] == 0){
            idex_write.RegDst = 1;
            idex_write.ALUSrc = 0;
            idex_write.ALUOp = 0x10;
            idex_write.MemRead = 0;
            idex_write.MemWrite = 0;
            idex_write.MemToReg = 0;
            idex_write.RegWrite = 1;
            idex_write.ReadReg1Value = Regs[ifid_read.disArray[1]];
            idex_write.ReadReg2Value = Regs[ifid_read.disArray[2]];
            idex_write.SEOffset = 0x00000000;
            idex_write.WriteReg_20_16 = ifid_read.disArray[2];
            idex_write.WriteReg_15_11 = ifid_read.disArray[3];
            idex_write.Function = ifid_read.disArray[4];
            if( ifid_read.disArray[4] == 0x20)
                idex_write.Opfcn = "add";
            else if( ifid_read.disArray[4] == 0x22)
                idex_write.Opfcn = "sub";
        }
        else if(ifid_read.disArray[0] == 0x20){
            idex_write.RegDst = 0;
            idex_write.ALUSrc = 1;
            idex_write.ALUOp = 0x00;
            idex_write.MemRead = 1;
            idex_write.MemWrite = 0;
            idex_write.MemToReg = 1;
            idex_write.RegWrite = 1;
            idex_write.ReadReg1Value = Regs[ifid_read.disArray[1]];
            idex_write.ReadReg2Value = Regs[ifid_read.disArray[2]];
            idex_write.SEOffset = ifid_read.disArray[3];
            idex_write.WriteReg_20_16 = ifid_read.disArray[2];
            idex_write.WriteReg_15_11 = 0;
            idex_write.Function = 0;
            idex_write.Opfcn = "lb";
        }

        else if(ifid_read.disArray[0] == 0x28){
            idex_write.RegDst = 0;
            idex_write.ALUSrc = 1;
            idex_write.ALUOp = 0x00;
            idex_write.MemRead = 0;
            idex_write.MemWrite = 1;
            idex_write.MemToReg = 0;
            idex_write.RegWrite = 0;
            idex_write.ReadReg1Value = Regs[ifid_read.disArray[1]];
            idex_write.ReadReg2Value = Regs[ifid_read.disArray[2]];
            idex_write.SEOffset = ifid_read.disArray[3];
            idex_write.WriteReg_20_16 = ifid_read.disArray[2];
            idex_write.WriteReg_15_11 = 0;
            idex_write.Function = 0;
            idex_write.Opfcn = "sb";
        }
    }
    else{
            idex_write.Opfcn = "nop";
            idex_write.RegDst = 0;
            idex_write.ALUSrc = 0;
            idex_write.ALUOp = 0x00;
            idex_write.MemRead = 0;
            idex_write.MemWrite = 0;
            idex_write.MemToReg = 0;
            idex_write.RegWrite = 0;
            idex_write.ReadReg1Value = 0;
            idex_write.ReadReg2Value = 0;
            idex_write.SEOffset = 0x00000000;
            idex_write.WriteReg_20_16 = 0;
            idex_write.WriteReg_15_11 = 0;
            idex_write.Function = 0;
    }
}

/* Performs the requested instruction on the specific operands you read out of the READ version of the IDEX pipeline register */
void EX_stage(){

    if( idex_read.Opfcn.compare(0,3,"nop") != 0){
        exmem_write.noOp = false;
        exmem_write.MemRead = idex_read.MemRead;
        exmem_write.MemWrite = idex_read.MemWrite;
        exmem_write.MemToReg = idex_read.MemToReg;
        exmem_write.RegWrite = idex_read.RegWrite;

        exmem_write.Opfcn = idex_read.Opfcn;
        exmem_write.SBValue = idex_read.ReadReg2Value & 0x000000FF;

        if (exmem_write.Opfcn.compare(0,2,"sb") == 0){
            exmem_write.ALUResult = idex_read.ReadReg1Value + idex_read.SEOffset;
            exmem_write.WriteRegNum = 0;
        }

        else if (exmem_write.Opfcn.compare(0,2,"lb") == 0){
            exmem_write.ALUResult = idex_read.ReadReg1Value + idex_read.SEOffset;
            exmem_write.WriteRegNum = idex_read.WriteReg_20_16;
        }

        else if (exmem_write.Opfcn.compare(0,2,"ad") == 0){
            exmem_write.ALUResult = idex_read.ReadReg1Value + idex_read.ReadReg2Value;
            exmem_write.WriteRegNum = idex_read.WriteReg_15_11;
        }

        else if (exmem_write.Opfcn.compare(0,2,"su") == 0){
            exmem_write.ALUResult = idex_read.ReadReg1Value - idex_read.ReadReg2Value;
            exmem_write.WriteRegNum = idex_read.WriteReg_15_11;
        }
    }
    else{
        exmem_write.MemRead = 0;
        exmem_write.MemWrite = 0;
        exmem_write.MemToReg = 0;
        exmem_write.RegWrite = 0;

        exmem_write.Opfcn = "nop";
        exmem_write.SBValue = 0;
    }
}

/* For lb, uses the address you calculated in the EX stage as an index into your Main Memory array and get the value that is there */
void MEM_stage(){

    std::stringstream stream;
    if( exmem_read.Opfcn.compare(0,3,"nop") != 0){
        memwb_write.noOp = false;
        memwb_write.MemToReg = exmem_read.MemRead;
        memwb_write.RegWrite = exmem_read.RegWrite;
        memwb_write.ALUResult = exmem_read.ALUResult;
        memwb_write.WriteRegNum = exmem_read.WriteRegNum;
        memwb_write.Opfcn = exmem_read.Opfcn;

        if (memwb_write.Opfcn.compare(0,2,"sb") == 0){
            memwb_write.LBDataValue = 0;
            Main_Mem[memwb_write.ALUResult] = exmem_read.SBValue;
            stream << "(Value " << exmem_read.SBValue << " has been written to memory address " << hex << memwb_write.ALUResult << " in the MEM stage)";
            memwb_write.outStatement = stream.str();
        }

        else if (memwb_write.Opfcn.compare(0,2,"lb") == 0){
            memwb_write.LBDataValue = Main_Mem[memwb_write.ALUResult];
            stream << "($" << memwb_write.WriteRegNum << " will be set to a new value of " << hex << setfill('0') << setw(2) << memwb_write.LBDataValue << " in the WB stage)";
            memwb_write.outStatement = stream.str();
        }

        else if (memwb_write.Opfcn.compare(0,2,"ad") == 0){
            memwb_write.LBDataValue = 0;
            stream << "($" << memwb_write.WriteRegNum << " will be set to a new value of " << hex << memwb_write.ALUResult << " in the WB stage)";
            memwb_write.outStatement = stream.str();
        }

        else if (memwb_write.Opfcn.compare(0,2,"su") == 0){
            memwb_write.LBDataValue = 0;
            stream << "($" << memwb_write.WriteRegNum << " will be set to a new value of " << hex << memwb_write.ALUResult << " in the WB stage)";
            memwb_write.outStatement = stream.str();
        }
    }
    else{

        memwb_write.MemToReg = 0;
        memwb_write.RegWrite = 0;
        memwb_write.ALUResult = 0;
        memwb_write.WriteRegNum = 0;
        memwb_write.LBDataValue = 0;
        memwb_write.Opfcn = "nop";
    }
}

/* Write to the registers based on information you read out of the READ version of MEM_WB */
void WB_stage(){

    if( memwb_read.Opfcn.compare(0,3,"nop") != 0){

        if (memwb_read.Opfcn.compare(0,2,"lb") == 0){
            Regs[memwb_read.WriteRegNum] = memwb_read.LBDataValue;
        }
        else if (memwb_read.Opfcn.compare(0,2,"ad") == 0 || memwb_read.Opfcn.compare(0,2,"su") == 0){
            Regs[memwb_read.WriteRegNum] = memwb_read.ALUResult;
        }
    }
}


/* Populates the values of the Main Memory array with hex 0-F corresponding to the address*/
void populateMM(){

    short counter = 0;
    for(int i=0; i<1024; i++){

        Main_Mem[i] = counter;
        if( counter == 0xFF)
            counter = 0;
        else
            counter++;
    }
}

/* Populates the initial value of the Registry */
void populateREG(){

    for(int i = 0; i<32; i++){
        Regs[i] = 0x100 + i;
    }
}

/* Prints out the pipeline */
void Print_out_everything(){

    string nop = "Control = 00000000";

    cout << "IF/ID Write (written to by the IF stage)" << endl;
    if(ifid_write.noOp){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Inst = " << hex << setfill('0') << setw(8) << ifid_write.instruction << "   [" << ifid_write.instr_disassembled << "]" << "\n" << endl;
    }

    cout << "IF/ID Read (read by the ID stage)" << endl;
    if(ifid_read.noOp ){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Inst = " << hex << setfill('0') << setw(8) << ifid_read.instruction << "   [" << ifid_read.instr_disassembled << "]" << "\n" << endl;
    }

    cout << "ID/EX Write (written to by the ID stage)" << endl;
    if(idex_write.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Control: RegDst = " << idex_write.RegDst << " , ALUSrc = " << idex_write.ALUSrc << " , ALUOp = " << hex << setfill('0') << setw(2) << idex_write.ALUOp << " , MemRead = " << dec << idex_write.MemRead << endl;
        cout << "       MemWrite = " << idex_write.MemWrite << " , MemToReg = " << idex_write.MemToReg << " , RegWrite = " << idex_write.RegWrite << " [" << idex_write.Opfcn  << "]" << endl;
        cout << "Fields: ReadReg1Value = " << hex << idex_write.ReadReg1Value << " , ReadReg2Value = " << idex_write.ReadReg2Value << " , SEOffset = " << setfill('0') << setw(8) << idex_write.SEOffset << endl;
        cout << "       WriteReg_20_16 = " << dec << idex_write.WriteReg_20_16 << " , WriteReg_15_11 = " << dec << idex_write.WriteReg_15_11 << " , Function = " << hex << idex_write.Function << "\n" << endl;
    }

    cout << "ID/EX Read (read by the EX stage)" << endl;
    if(idex_read.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Control: RegDst = " << idex_read.RegDst << " , ALUSrc = " << idex_read.ALUSrc << " , ALUOp = " << hex << setfill('0') << setw(2) << idex_read.ALUOp << " , MemRead = " << dec << idex_read.MemRead << endl;
        cout << "       MemWrite = " << idex_read.MemWrite << " , MemToReg = " << idex_read.MemToReg << " , RegWrite = " << idex_read.RegWrite << " [" << idex_read.Opfcn  << "]" << endl;
        cout << "Fields: ReadReg1Value = " << hex << idex_read.ReadReg1Value << " , ReadReg2Value = " << idex_read.ReadReg2Value << " , SEOffset = " << setfill('0') << setw(8) << idex_read.SEOffset << endl;
        cout << "       WriteReg_20_16 = " << dec << idex_read.WriteReg_20_16 << " , WriteReg_15_11 = " << dec << idex_read.WriteReg_15_11 << " , Function = " << hex << idex_read.Function << "\n" << endl;
    }

    cout << "EX/MEM Write (written to by the EX stage)" << endl;
    if(exmem_write.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Control: MemRead = " << exmem_write.MemRead << ",  MemWrite = " << exmem_write.MemWrite << " , MemToReg = " << exmem_write.MemToReg << ", RegWrite = " << exmem_write.RegWrite << " [" << exmem_write.Opfcn << "]" << endl;
        cout << "Fields: ALUResult = " << hex << exmem_write.ALUResult <<  " , SBValue = "  << setfill('0') << setw(2) << exmem_write.SBValue << " , WriteRegNum = " << dec << exmem_write.WriteRegNum << "\n" << endl;
    }

    cout << "EX/MEM Read (read by the MEM stage)" << endl;
    if(exmem_read.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << "\n" << endl;
    }
    else{
        cout << "Control: MemRead = " << exmem_read.MemRead << ",  MemWrite = " << exmem_read.MemWrite << " , MemToReg = " << exmem_read.MemToReg << ", RegWrite = " << exmem_read.RegWrite << " [" << exmem_read.Opfcn << "]" << endl;
        cout << "Fields: ALUResult = " << hex << exmem_read.ALUResult <<  " , SBValue = " << setfill('0') << setw(2) << exmem_read.SBValue << " , WriteRegNum = " << dec << exmem_read.WriteRegNum << "\n" << endl;
    }

    cout << "MEM/WB Write (written to by the MEM stage)" << endl;
    if(memwb_write.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << "\n" << endl;
    }
    else{

        cout << "Control: MemToReg = " << memwb_write.MemToReg << " , RegWrite = " << memwb_write.RegWrite << " [" << memwb_write.Opfcn << "]" << endl;
        cout << "Fields: LBDataValue = " << hex << setfill('0') << setw(2) << memwb_write.LBDataValue  << " , ALUResult = " << memwb_write.ALUResult <<  " , WriteRegNum = " << dec << memwb_write.WriteRegNum << endl;
        cout << memwb_write.outStatement << "\n" <<  endl;
    }

    cout << "MEM/WB Read (read by the WB stage)" << endl;
    if(memwb_read.Opfcn.compare(0,3,"nop") == 0){
        cout << nop << endl;
    }
    else{

        cout << "Control: MemToReg = " << memwb_read.MemToReg << " , RegWrite = " << memwb_read.RegWrite << " [" << memwb_read.Opfcn << "]" << endl;
        cout << "Fields: LBDataValue = " << hex << setfill('0') << setw(2) << memwb_read.LBDataValue  << " , ALUResult = " << memwb_read.ALUResult <<  " , WriteRegNum = " << dec << memwb_read.WriteRegNum << endl;
        cout << memwb_read.outStatement << "\n" << endl;
    }
    cout << endl;

    //Print the Registry
    printREG();

}

void Copy_write_to_read(){

    // Copy IFID to read
        ifid_read.noOp = ifid_write.noOp;
        ifid_read.instruction = ifid_write.instruction;
        ifid_read.instr_disassembled = ifid_write.instr_disassembled;
        for(int i = 0; i<5; i++)
            ifid_read.disArray[i] = ifid_write.disArray[i];
        ifid_write.noOp = true;
        //memwb_read.noOp = true;


    // Copy IDEX to read
        idex_read.noOp = false;
        idex_read.ALUOp = idex_write.ALUOp;
        idex_read.ALUSrc = idex_write.ALUSrc;
        idex_read.Function = idex_write.Function;
        idex_read.MemRead = idex_write.MemRead;
        idex_read.MemToReg = idex_write.MemToReg;
        idex_read.MemWrite = idex_write.MemWrite;
        idex_read.Opfcn = idex_write.Opfcn;
        idex_read.ReadReg1Value = idex_write.ReadReg1Value;
        idex_read.ReadReg2Value = idex_write.ReadReg2Value;
        idex_read.RegDst = idex_write.RegDst;
        idex_read.RegWrite = idex_write.RegWrite;
        idex_read.SEOffset = idex_write.SEOffset;
        idex_read.WriteReg_15_11 = idex_write.WriteReg_15_11;
        idex_read.WriteReg_20_16 = idex_write.WriteReg_20_16;
        idex_write.noOp = true;
        //ifid_read.noOp = true;


    // Copy  EXMEM to read
        exmem_read.noOp = false;
        exmem_read.ALUResult = exmem_write.ALUResult;
        exmem_read.MemRead = exmem_write.MemRead;
        exmem_read.MemToReg = exmem_write.MemToReg;
        exmem_read.MemWrite = exmem_write.MemWrite;
        exmem_read.Opfcn = exmem_write.Opfcn;
        exmem_read.RegWrite = exmem_write.RegWrite;
        exmem_read.SBValue = exmem_write.SBValue;
        exmem_read.WriteRegNum = exmem_write.WriteRegNum;
        exmem_write.noOp = true;
        //idex_read.noOp = true;


    // Copy MEMWB to read
        memwb_read.noOp = false;
        memwb_read.ALUResult = memwb_write.ALUResult;
        memwb_read.LBDataValue = memwb_write.LBDataValue;
        memwb_read.MemToReg = memwb_write.MemToReg;
        memwb_read.Opfcn = memwb_write.Opfcn;
        memwb_read.RegWrite = memwb_write.RegWrite;
        memwb_read.outStatement = memwb_write.outStatement;
        memwb_read.WriteRegNum = memwb_write.WriteRegNum;
        memwb_write.noOp = true;
        //exmem_read.noOp = true;

}

void printREG(){

    cout << "Registry" << endl;
    for(int i = 0; i<32; i++){

        cout << "$" << dec << setfill('0') << setw(2) << i << "=" << hex << setfill('0') << setw(3) << Regs[i] << "  ";
        if( i%8 == 7)
            cout << endl;

    }
    cout << endl;
}

void printMM(){

    cout << "Main Memory" << endl;
    int addr = 0x000;
    int i = 0;

    do{
        if(i%16 == 0){
            cout << "For addresses " << hex << setfill('0') << setw(3) << addr << " to ";
            addr+=15;
            cout << setfill('0') << setw(3) <<addr++ << ": ";
        }

        cout << hex << setfill('0') << setw(2) << Main_Mem[i] << " ";
        i++;
        if( i%16 == 0){
                cout << endl;
        }
    }while(i<1024);
}
