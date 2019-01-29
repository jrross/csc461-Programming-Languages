class ReverseIterator:
    '''
    :file: ReverseIterator.py
    :author: Jeff Ross
    :details: Handles a reverse iterator class to use the iterator pattern.
    '''
    def __init__(self, coll):
        '''
        Constructor for the class, holding the array being passed in
        :param coll: the array to be held
        '''
        self.collection = coll
        self.index = len(coll) - 1

    def __iter__(self):
        '''
        __iter__
        returns self to be used for iteration
        :return: self
        '''
        return self

    def __next__(self):
        '''
        __next__
        steps backward in the array and returns the value there. decrements index
        :return: value backwards by one in current index
        '''
        if self.index < 0:
            raise StopIteration
        self.index = self.index - 1
        return self.index + 1, self.collection[self.index + 1]