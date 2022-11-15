import java.util.*;

public class ChemicalReaction {
	

	public boolean solution(String inputStr) {
		Map<String, Integer> leftAtoms = new HashMap<>();
		Map<String, Integer> rightAtoms = new HashMap<>();

		int currentMultiplier = 1;
		String currentAtom = "";
		String parseMultiplier = "";
		boolean isLeftSide = true;
		int amount = 0;

		for (int i = 0; i < inputStr.length(); i++) {

			//for each character we need to see
			//1. if it's a number or char
			//2. if it's a number, is it the beginning one or bottom one
			//3. if it's a char, is it Upper or lower case
			//3.a. Upper case, if it's the same with last digit return false; if not, former stack can be either a finished number(1, 2 or more digits) or element pop stack
			//
			if (Character.isDigit(inputStr.charAt(i))) {
				parseMultiplier += inputStr.charAt(i);
				while (i < inputStr.length() - 1 && Character.isDigit(inputStr.charAt(i + 1))) {
					parseMultiplier += inputStr.charAt(i + 1);
					i++;
				}

				currentMultiplier = Integer.parseInt(parseMultiplier);
				parseMultiplier = "";
			} else if (Character.isUpperCase(inputStr.charAt(i))) {
				if (i > 0 && inputStr.charAt(i) == inputStr.charAt(i - 1)) return false;
				currentAtom += inputStr.charAt(i);

				while(i < inputStr.length() - 1 && Character.isLowerCase(inputStr.charAt(i + 1))){
                    currentAtom += inputStr.charAt(i+1);
                    i++;
                }

                while(i < inputStr.length() - 1 && Character.isDigit(inputStr.charAt(i + 1))){
                    parseMultiplier += inputStr.charAt(i + 1);
                    i++;
                }

                if(parseMultiplier.length()==0) parseMultiplier = "1";

                if (isLeftSide)  {

                	amount = currentMultiplier * Integer.parseInt(parseMultiplier);
                    if(!leftAtoms.containsKey(currentAtom)){
                        leftAtoms.put(currentAtom, amount);
                    }else{
                        amount += leftAtoms.get(currentAtom);
                        leftAtoms.put(currentAtom, amount);
                    }
                } else {
                    amount = currentMultiplier * Integer.parseInt(parseMultiplier);
                    if(!rightAtoms.containsKey(currentAtom)){
                        rightAtoms.put(currentAtom,amount);
                    }else{
                        amount += rightAtoms.get(currentAtom);
                        if(amount > leftAtoms.get(currentAtom)) return false;
                        rightAtoms.put(currentAtom,amount);
                    }
                }

                parseMultiplier = "";
                currentAtom = "";

			} else if (inputStr.charAt(i) == '=') {
				isLeftSide = false;
				currentMultiplier = 1;

			} else if (inputStr.charAt(i) == '+') {
				currentMultiplier = 1;
			}
		}
		if (leftAtoms.size() != rightAtoms.size()) return false;
		for(String atom : leftAtoms.keySet()){
//			System.out.println("left: " + atom + leftAtoms.get(atom));
//			System.out.println("right: " + atom + rightAtoms.get(atom));
            if(!rightAtoms.containsKey(atom)) return false;
            if(leftAtoms.get(atom) != rightAtoms.get(atom)) return false;
        }
        
        return true;


	}


	public static void main(String[] args) {

		ChemicalReaction test = new ChemicalReaction();

		String[] s1 = {"2H2 + O2 = 2H2O", //expect true
			"1000H2O = Au + Ag", //false
			"H2O + SO3 = H2SO4", //true
			"3Fe + 2O2 = Fe3O4", //true
			"Na + HCO3 = NaHCO3", //true
			"6CO2 + 6H2O = C6H12O6 + 6O2", //true
			"C6H12O6 + 6O2 + 6H2O = 6CO2 + 12H2O" //true
		};

		for (String s : s1) {
			System.out.println(test.solution(s));
		}

	}
}