
/**
 * Trees without explicit links.
 * Notice that various fields/methods have the protected modifier
 * when normally they would/should be private.
 * The reason is that this supports whitebox testing.
 *
 * @author Stefan Kahrs
 * @version 1
 */
//note the constraint on A is a slight generalisation of A extends Comparable<A>
//and is generally recommended when one wants a comparison operation
//it basically allows that the comparison op is implemented at a supertype
//of A, instead of A itself;
//for the assessment itself it makes no discernable difference
public class LinklessTree<A extends Comparable<? super A>>
{
	protected Object[] elems;
		
		//{ 6, 4, 9, 2, null, 7, 41};
	
		
    //for annoying technical reason this has to be an array of objects
	
    //sizes of subtrees at that node index
    protected int[] sizes; 

    	//{6, 2, 3, 1, 0, 1, 1};
  
    	
    

    
    /**
     * Constructor for objects of class LinklessTree
     */
    private static final int STARTSIZE=15;
    
    public LinklessTree()
    {
        assert STARTSIZE>0;
        elems = freshElemArray(STARTSIZE);
        sizes = new int[STARTSIZE];
        sizes[0] = 0; 
    }

    //size of whole tree is the size of the subtree rooted at 0
    public int size() 
    {
        return getSize(0);
    }
    
    
    public A getValue(int index) 
    {
        return (A)elems[index];
    }

    //auxiliary methods to index the arrays out of bounds too
    //they may help to reduce case distinctions
    protected A getKey(int subtree) 
    {
        if (subtree >= elems.length) 
        	return null; // out of bounds
        
        return getValue(subtree);
    }

    protected int getSize(int subtree) 
    {
        if (subtree >= elems.length) 
        	return 0; // out of bounds
        return sizes[subtree];
    }
    
    //encapsulates the cast on the allocation
    protected Object[] freshElemArray(int capacity) 
    {
        return new Object[capacity];
    }

    //remainder needs to be modified
    
    //find index position of val in tree, if there, or where it goes, if not there
    protected int findIndex(A val) 
    {
    	boolean found = false;
    	int index = 0;
    	
        while (!found) 
        {
        	if (index >= elems.length)
        	{
        		
        		found = true;
        		return index;		
        	}
        	else if (elems[index] == val)
        	{
        		found = true;
        		return index;
        	}
        	else if (val.compareTo((A) elems[index]) > 0 )
        	{
        		index = 2*index + 2;
        		
        	}
        	else if (val.compareTo((A) elems[index]) < 0)
        	{
        		index = 2*index + 1;
        	}
        	
        	
        	
        }
		return 0;
    }

    //is value in tree
    public boolean contains(A val) 
    {
        boolean found = false;
        int index = 0;
        
        while (!found && index < elems.length && elems[index] != null) 
        {
        	if (elems[index] == val)
        	{
        		found = true;
        	}
        	else if (val.compareTo((A) elems[index]) > 0 )
        	{
        		index = 2*index + 2;
        		
        	}
        	else if (val.compareTo((A) elems[index]) < 0)
        	{
        		index = 2*index + 1;
        	}
        }
        return found;
    }

    //grow the space in which we can palce the tree, so that at least one insertion will succeed
    protected void grow() 
    {
    	
    	Object [] newElems = freshElemArray(elems.length*2);
    	int [] newSizes = new int [sizes.length*2];
    	

    	for (int i = 0; i < elems.length; i ++)
    		newElems[i] = elems[i];
    		
    	for (int j=0; j < sizes.length; j ++)
    		newSizes[j] = sizes[j];
    	
	
    	elems = newElems;
    	sizes = newSizes;
    	
    	////////////////////////////////////////
    
    	/**
  
    	
    	int nodeCount = sizes[0]; //number of nodes yet to be checked
    	int currentRootNode = 0;
    	//boolean unbalanced = true;
    	
    	for (int i =0; i < nodeCount; i ++) //loops through 0-size of elems-1
    	{
    		 int workingIndex = (int) get(i); //gets position of each mode
    		
    		if (workingIndex*2+2 >= elems.length || elems[workingIndex *2+2] == null && workingIndex *2+1 >= elems.length || elems[workingIndex *2+1] == null  )
    		{ 
    			//No children, no balancing needed
    			
    		}
    		else if (sizes[workingIndex*2+1] - sizes[workingIndex*2+2] > 1)
    		{
    			//left node is greater than right node by more than 1
    			int sizeDif = sizes[workingIndex*2+1] - sizes[workingIndex*2+2];
    			
    			while (sizes[workingIndex*2+1] - sizes[workingIndex*2+2] > 1)
    			{
    				A node = (A) elems[workingIndex];
    				delete((A) elems[workingIndex]);
    				insert(node);
    			}
    			
    		
    			
    			
    		}
    		else if (sizes[workingIndex*2+2] -sizes[workingIndex*2+1] > 1 )
    		{
    			//right node is greater than left node by more than 1
    			
    			int sizeDif = sizes[workingIndex*2+2] -sizes[workingIndex*2+1];
    			
    			while (sizes[workingIndex*2+2] -sizes[workingIndex*2+1] > 1)
    			{
    				A node = (A) elems[workingIndex];
    				delete((A) elems[workingIndex]);
    				insert(node);
    			}
    		}
    		else
    		{
    			//node is balanced
    			
    			
    		}
    		
    	}
    	
		**/
    	

    }

    //fetch the i-th element, in comparsion order
    
    //sizes = {6,  2, 3, 1, 0,    1,  1}
    @SuppressWarnings("unchecked")
	public A get(int i)
    { 
    	int  currentRootNode = 0; //0 means you start searching from root node
   
    	if (i >= sizes[0])
    	{
    		System.out.println("Out of bounds");
    		return null;
   
    	}
    	else
    	{
    		
    		boolean found = false;
    		
    		while (!found)
        	{
    			if (i ==  sizes[currentRootNode * 2 +1]) //This means you are looking for the position the currentRootNode is actually in                                   
        		{
        			found = true;
        			return (A) elems[currentRootNode];
        		}
    			
    			else if (i < sizes[currentRootNode * 2 +1])//Left sub tree
        		{
        		
    				currentRootNode =  currentRootNode *2 + 1;

        		}
    			
    			
    			else if (i > sizes[currentRootNode * 2 + 1]) //right sub tree 
        		{
    				i = i - (sizes[currentRootNode*2+1] + 1); //As the ith value is in the right tree i needs to decrease as you are searcging a smaller subtree 
        			currentRootNode = currentRootNode *2 + 2;
        			
        			
        		}
    			
    			
				
				  if (currentRootNode * 2 + 1 >= sizes.length) 
				  {
					  	found = true; return (A)
					  	elems[currentRootNode]; 
				  }
				 
      
        	}

    		
    	}
    	
    	
		return null; 
    	
    	
    }

    //add x to tree, return true if tree was modified
    //we do not allow multiple copies of the equal objects in tree
    //equality is decided by using compareTo
    public boolean insert(A x)
    {
    	
    	boolean inserted = false;
    	int currentRootNode = 0;
    
    	if (!contains(x))
    	{
    		sizes[0]++;
    		while (!inserted)
        	{
        		if(currentRootNode *2+1 >= elems.length || currentRootNode *2+2 >= elems.length)
        		{
        			grow();
          		}
        		else if (((Comparable<? super A>) elems[currentRootNode *2+1]) == null &&  ((Comparable<? super A>) elems[currentRootNode]).compareTo(x) == 1)
        		{

        			elems[currentRootNode*2+1] = x;
        			inserted = true;
        			sizes[currentRootNode*2+1]++;
        				
    			}
        			
        		else if (elems[currentRootNode *2+2] == null &&  ((Comparable<? super A>) elems[currentRootNode]).compareTo(x) == -1)
        		{
        			elems[currentRootNode*2+2] = x;
        			inserted = true;     
        			sizes[currentRootNode*2+2]++;
        		}
        			
        		else if (((Comparable<? super A>) elems[currentRootNode]).compareTo(x) == 1)
        		{
        			//Current Root Node is greater than x, should move to left child
        			currentRootNode = currentRootNode * 2 + 1;
        			sizes[currentRootNode]++;
        		}
        		
        		else if (((Comparable<? super A>) elems[currentRootNode]).compareTo(x) == -1)
    			{
        			//Current root node is less than x, should move to right child
        			currentRootNode = currentRootNode*2+2;
        			sizes[currentRootNode]++;
    			}

        	}
    		
    	}
    	
    	return inserted;
    }

    //remove x from tree, return true if tree was modified
    public boolean delete(A x)
    { 
    	boolean deleted = false;
    	int index = findIndex(x);
    	int currentRootNode =0;
    	
    	
    	if (index >= sizes.length)
    	{
    		//out of range, do nothing
    	}
    
    	
    	else if (sizes[index] == 1)
    	{
    		currentRootNode = index;
    		//has no children nodes so no adjusting of elems needed
    		elems[index] = null;
    		sizes[index] = 0;
    		
    	
  
    		
    		deleted = true;
    	}
    	
    	
    	
    	else if (sizes[index*2+1] - sizes[index*2+2] > 0) //This means left subtree is larger
    	{
    		currentRootNode = index*2+1;
    		
    		//largest from left tree
    		while (currentRootNode*2+2 < elems.length)
    		{
    			if (elems[currentRootNode*2+2] == null)
    				break;
    		
    			//This means either the right child is not null or the array still has room for another right child
    			
    			currentRootNode = currentRootNode*2+2;
    		}
    		
    		elems[index] = elems[currentRootNode];
    		elems[currentRootNode] = null;
    		
    		
    		
    		deleted = true;
    		
    		
    	}
    	
    	//smallest from right tree
    	else //either right is bigger or they are the same, so just choose right
    	{
    		currentRootNode = index*2+2;
    		
    		while (currentRootNode*2+1 < elems.length  )
    		{
    			
    			if (elems[currentRootNode*2+1] == null)
    				break;
    			
    			//This means either the left child is not null or the array still has room for another left child
    			
    			currentRootNode = currentRootNode*2+1;
    		}
    		
    		elems[index] = elems[currentRootNode];
    		elems[currentRootNode] = null;
    		sizes[currentRootNode]--;
    		
    		
    		
    		deleted = true;
    		
    		
    	}
    	
    	while(currentRootNode > 0)
		{
    		
			currentRootNode = (int) Math.floor((currentRootNode-1)/2);
			sizes[currentRootNode]--;
		}
    	
    	
    	
    	return deleted;
    }

    //not requested, but these might be useful auxiliary ops for delete
    private A deleteLargest(int subtree) 
    { 
    	return null;
    }

    private A deleteSmallest(int subtree) 
    { 
    	return null; 
    	}
    
    public void outputArrays()
    {
    	for (Object A: elems)
    	{
    		System.out.print(A + " ");
    	}
    	
    	System.out.println();
    	
    	for(int i : sizes)
    	{
    		System.out.print(i + " ");
    	}
    	System.out.println();
    }
}
