/**
 * Created by oscar on 3/1/16.
 */
public class MedianOfTwoSortedArrays
{
    // Recommended method ported from:
    // (http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/)
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        // Both two arrays are empty is not considered
        if (nums1.length == 0 && nums2.length > 0)
        {
            return findMid(nums2, 0, nums2.length - 1);
        }
        else if (nums2.length == 0 && nums1.length > 0)
        {
            return findMid(nums1, 0, nums1.length - 1);
        }
        else if (nums1.length == 1 && nums2.length == 1)
        {
            return (nums1[0] + nums2[0]) * 0.5f;
        }
        else if (nums1.length == 1)
        {
            return oneAndMore(nums1[0], nums2, 0, nums2.length - 1);
        }
        else if (nums2.length == 1)
        {
            return oneAndMore(nums2[0], nums1, 0, nums1.length - 1);
        }
        else if (nums1.length == 2 && nums2.length > 2)
        {
            return twoAndMore(nums1[0], nums1[1], nums2, 0, nums2.length - 1);
        }
        else if (nums2.length == 2 && nums1.length > 2)
        {
            return twoAndMore(nums2[0], nums2[1], nums1, 0, nums1.length - 1);
        }
        else
        {
            return medianCompare(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1);
        }
    }

    public float oneAndMore(int num1, int[] nums2, int start2, int end2)
    {
        int nums2Len = end2 - start2 + 1;

        if (nums2Len % 2 == 0)
        {
            int nums2MidLeft = nums2[start2 + nums2Len / 2 - 1];
            int nums2MidRight = nums2[start2 + nums2Len / 2];

            if (num1 < nums2MidLeft)
            {
                return nums2MidLeft;
            }
            else if (num1 < nums2MidRight)
            {
                return num1;
            }
            else
            {
                return nums2MidRight;
            }
        }
        else
        {
            int nums2MidLeft = nums2[start2 + nums2Len / 2 - 1];
            int nums2MidMid = nums2[start2 + nums2Len / 2];
            int nums2MidRight = nums2[start2 + nums2Len / 2 + 1];

            if (num1 < nums2MidLeft)
            {
                return (nums2MidLeft + nums2MidMid) * 0.5f;
            }
            else if (num1 < nums2MidMid)
            {
                return (num1 + nums2MidMid) * 0.5f;
            }
            else if (num1 < nums2MidRight)
            {
                return (num1 + nums2MidMid) * 0.5f;
            }
            else
            {
                return (nums2MidMid + nums2MidRight) * 0.5f;
            }
        }
    }

    public float twoAndMore(int nums1_first, int nums1_second, int[] nums2, int start2, int end2)
    {
        int nums2Len = end2 - start2 + 1;

        if (nums2Len % 2 == 0)
        {
            int nums2MidRightRight = nums2[start2 + nums2Len / 2 + 1];
            int nums2MidRight = nums2[start2 + nums2Len / 2];
            int nums2MidMid = nums2[start2 + nums2Len / 2 - 1];
            int nums2MidLeft = nums2[start2 + nums2Len / 2 - 2];

            return findMidOfFour(
                    nums2MidRight,
                    nums2MidMid,
                    Math.max(nums1_first, nums2MidLeft),
                    Math.min(nums1_second, nums2MidRightRight));
        }
        else
        {
            int nums2MidMid = nums2[start2 + nums2Len / 2];
            int nums2MidLeft = nums2[start2 + nums2Len / 2 - 1];
            int nums2MidRight = nums2[start2 + nums2Len / 2 + 1];

            return findMidOfThree(nums2MidMid, Math.max(nums1_first, nums2MidLeft), Math.min(nums1_second, nums2MidRight));
        }
    }

    public float findMidOfFour(int a, int b, int c, int d)
    {
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        int min = Math.min(a, Math.min(b, Math.min(c, d)));

        return (a + b + c + d - max - min) * 0.5f;
    }

    public float findMidOfThree(int a, int b, int c)
    {
        return a + b + c - Math.min(a, Math.min(b, c)) - Math.max(a, Math.max(b, c));
    }

    public float medianCompare(int[] nums1, int[] nums2, int start1, int end1, int start2, int end2)
    {
        float mid1 = findMid(nums1, start1, end1);
        float mid2 = findMid(nums2, start2, end2);
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        if (mid1 == mid2)
        {
            return mid1;
        }
        else if (mid1 > mid2)
        {
            if (len1 == 2 && len2 == 2)
            {
                return findMidOfFour(nums1[start1], nums2[start2], nums1[end1], nums2[end2]);
            }
            else if (len1 == 2)
            {
                return twoAndMore(nums1[start1], nums1[end1], nums2, start2, end2);
            }
            else if (len2 == 2)
            {
                return twoAndMore(nums2[start2], nums2[end2], nums1, start1, end1);
            }
            else
            {
                int ignoredLength;

                if (nums1.length > nums2.length)
                {
                    ignoredLength = (len2 - 1) / 2;
                }
                else
                {
                    ignoredLength = (len1 - 1) / 2;
                }

                end1 = end1 - ignoredLength;
                start2 = start2 + ignoredLength;

                return medianCompare(nums1, nums2, start1, end1, start2, end2);
            }
        }
        else
        {
            if (len1 == 2 && len2 == 2)
            {
                return findMidOfFour(nums1[start1], nums2[start2], nums1[end1], nums2[end2]);
            }
            else if (len1 == 2)
            {
                return twoAndMore(nums1[start1], nums1[end1], nums2, start2, end2);
            }
            else if (len2 == 2)
            {
                return twoAndMore(nums2[start2], nums2[end2], nums1, start1, end1);
            }
            else
            {
                int ignoredLength;

                if (nums1.length > nums2.length)
                {
                    ignoredLength = (len2 - 1) / 2;
                }
                else
                {
                    ignoredLength = (len1 - 1) / 2;
                }

                start1 = start1 + ignoredLength;
                end2 = end2 - ignoredLength;

                return medianCompare(nums1, nums2, start1, end1, start2, end2);
            }
        }
    }

    public float findMid(int[] nums, int start, int end)
    {
        int len = end - start + 1;

        if (len % 2 == 0)
        {
            return (nums[start + len / 2] + nums[start + len / 2 - 1]) * 0.5f;
        }
        else
        {
            return nums[start + len / 2];
        }
    }
}
