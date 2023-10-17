# Lexical_Landscape

## General Information
**Name:** Yuyang Wang  
**Date:** 4/17/2023  
**Title:** Checking word frequency using BST map and Hash map

## Abstract
This project investigates the use of different Mapset implementations, specifically Hashmap and BSTmap, to analyze word frequency within a large text document. The program reads the text, extracts words using REGEX, and then constructs a map noting the frequency of each word. Common words are excluded from the analysis. The aim is to determine the most frequently used words annually and compare the time efficiency of different map structures in processing this data.

## Exploration

### 1: Word Frequency Analysis
The analysis focuses on the frequency of specific words, ignoring common words predetermined or identified as consistently high-frequency. Notable findings include the prevalence of certain words in specific years, potentially indicating societal trends or interests at the time.

**Table 1:** The most frequent words each year. (The output of MainHash.java)

| Year    | 2008   | 2009  | 2010  | 2011   | 2012 | 2013 | 2014 | 2015 |
|---------|--------|-------|-------|--------|------|------|------|------|
| Most frequent word | better | thing | thing | always | look | look | game | game |

The project also analyzes the frequency of the word "Obama" over the years, revealing interesting patterns possibly linked to the political climate and events.

**Table 2:** The word “obama” appearing times and its appearing frequency. (The output of MainHash.java)

| Year    | 2008  | 2009 | 2010 | 2011 | 2012 | 2013 | 2014 | 2015 |
|---------|-------|------|------|------|------|------|------|------|
| The word "obama" appearing times | 11407 | 6974 | 3866 | 3414 | 2550 | 1642 | 904  | 829  |
| The word "obama" appearing frequency | 0.0024 | 0.0006 | 0.00029 | 0.00028 | 0.00022 | 0.00014 | 0.00007 | 0.0000069 |

### 2: Data Structure Efficiency
The project compares the efficiency of hashmap and BST map in terms of the time taken to build the map structures.

**Table 3:** The time taken for building maps of different structures. (The output of MainHash.java and MainBST.java)

| Year    | 2008  | 2009  | 2010  | 2011  | 2012  | 2013  | 2014  | 2015  |
|---------|-------|-------|-------|-------|-------|-------|-------|-------|
| Time taken for hash map (ms) | 2519  | 6684  | 8880  | 8671  | 7162  | 5647  | 6125  | 6273  |
| Time taken for BST map (ms)  | 15722 | 29077 | 24712 | 24115 | 21329 | 18903 | 20539 | 24167 |
| Total words                  | 12308487 | 30006889 | 34863964 | 32166119 | 29740218 | 28944670 | 30513654 | 30259997 |

### 3: Depth of Data Structures
Analyzing the maximum depth of the data structures post map construction.

**Table 4:** The max depth of hash map and BST map (The output of MainHash.java and MainBST.java)

| Year    | 2008 | 2009 | 2010 | 2011 | 2012 | 2013 | 2014 | 2015 |
|---------|------|------|------|------|------|------|------|------|
| Max depth of hash map | 5  | 7  | 6  | 7  | 6  | 6  | 6  | 6  |
| Max depth of BST map  | 77 | 45 | 44 | 43 | 47 | 43 | 44 | 49 |

## Extensions

### Ex1: Building an AVL tree
An exploration into AVL trees, a self-balancing binary search tree, and its efficiency compared to BSTs.

**Table 5:** The number of Unbalanced node and max depth for AVL tree and BST tree. (The output of MainAVL.java and MainBST.java)

| Year    | 2008 | 2009 | 2010 | 2011 | 2012 | 2013 | 2014 | 2015 |
|---------|------|------|------|------|------|------|------|------|
| number of the unbalanced node for AVL tree | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| number of the unbalanced node for BST tree | 20154 | 34467 | 37324 | 37500 | 38762 | 40874 | 45453 | 50474 |
| Max Depth for AVL tree | 20 | 21 | 21 | 21 | 21 | 21 | 21 | 21 |
| Max Depth for BST tree | 77 | 45 | 44 | 43 | 47 | 43 | 44 | 49 |

### Ex2: ArrayList-based Map Performance
Comparison of processing times between an ArrayList-based map and AVL map.

![image](https://github.com/ydotwang/Lexical_Landscape/assets/116990040/68a22332-6fa1-4e74-85ac-b6dec9a3714b)

*Figure:* The time taken for putting and getting the elements. (output of MapComparison.java)

### Ex3: AVL vs BST Efficiency
Further exploration into the performance differences between AVL and BST trees in terms of insertion and retrieval times.

![image](https://github.com/ydotwang/Lexical_Landscape/assets/116990040/89ae3232-5ef9-4727-b78b-aa4e57a3faa1)

*Figure 3:* The time taken for putting and getting the elements. (output of AVLvsBSTComparison.java)

## Reflections
A comprehensive reflection on the efficiency, order preservation, and balance maintenance of HashMaps, BSTMaps, and AVL trees, highlighting the advantages and potential issues associated with each data structure.

## Reference
1. [English Word Frequency datasets](https://www.kaggle.com/datasets/rtatman/english-word-frequency)
2. Ariana He, personal communication, 4/16/2023, Colby College.
